package com.caffeinedoctor.userservice.security.oauth2.handler;

import com.caffeinedoctor.userservice.entitiy.Refresh;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.repository.RefreshRepository;
import com.caffeinedoctor.userservice.security.oauth2.dto.CustomOAuth2User;
import com.caffeinedoctor.userservice.enums.UserStatus;
import com.caffeinedoctor.userservice.security.jwt.JWTUtil;
import com.caffeinedoctor.userservice.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

//로그인 성공 핸들러
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final RefreshRepository refreshRepository;

    @Value("${FRONT_URL}")
    private String redirectFrontURL;

    //로그인이 성공하면 동작
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("인증 완료! 소셜 로그인 성공");

        //유저 정보
        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        //username: kakao_12345
        String username = customUserDetails.getName();
        //role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();


        //토큰 2개 생성 (jwt 만들기 - 유저이름, 역할, 토큰이 살아있는 시간)
        //10분
        String access = jwtUtil.createJwt("access", username, role, 600000L);
        //24시간
        String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장
        addRefreshEntity(username, refresh, 86400000L);

        //응답 설정
        //헤더에 넣기
        response.setHeader("access", access);
        //쿠키에 넣기
        response.addCookie(createCookie("refresh", refresh));
        //상태 코드: 200 응답 보내기
        response.setStatus(HttpStatus.OK.value());

        // Check user status
        UserStatus userStatus = userService.getUserStatusByUsername(username);
        if (userStatus == UserStatus.NEW_USER) {
            // Redirect to signup page for new users
            response.sendRedirect(redirectFrontURL+"/signup");
        } else {
            // Redirect to home page for existing users
            response.sendRedirect(redirectFrontURL+"/home");
        }

    }

    //쿠키 만들기
    private Cookie createCookie(String key, String value) {
        //value: jwt
        Cookie cookie = new Cookie(key, value);
        //쿠키가 살아있을 시간
        cookie.setMaxAge(24*60*60);
        //https 통신에서만 사용 가능
        //cookie.setSecure(true);
        //쿠키 적용 범위 (전역)
        //cookie.setPath("/");
        //자바스크립트가 해당 쿠키를 가져가지 못하게 설정
        cookie.setHttpOnly(true);

        return cookie;
    }

    //refresh토큰 저장
    private void addRefreshEntity(String username, String newRefreshToken, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = Refresh.builder()
                .username(username)
                .refreshToken(newRefreshToken)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refreshEntity);
    }
}