package com.caffeinedoctor.userservice.security.oauth2;

import com.caffeinedoctor.userservice.dto.response.oauth2.CustomOAuth2User;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final UserService userService;
    @Value("${FRONT_URL}")
    private String redirectFrontURL;

    // 로그인이 성공하면 동작
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("인증 완료! 소셜 로그인 성공");
        log.info("쿠키에 JWT 토큰 저장 로직 수행");

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        //username: kakao_12345
        String username = customUserDetails.getName();
        //role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        //jwt 만들기 (유저이름, 역할, 토큰이 살아있는 시간) : 60초 * 60분 * 60시간 = 2.5일
        String token = jwtUtil.createJwt(username, role, 60*60*60L);
        log.info("JWT 토큰 생성: " + token);
        log.info("인가 권한을 가진 JWT 단일 토큰으로 발급 완료!");

        //쿠키 방식으로 토큰 전달
        response.addCookie(createCookie("Authorization", token));

        // Check user status
        UserStatus userStatus = userService.getUserStatusByUsername(username);
        if (userStatus == UserStatus.NEW_USER) {
            // Redirect to signup page for new users
            response.sendRedirect(redirectFrontURL+"/signup");
        } else {
            // Redirect to home page for existing users
            response.sendRedirect(redirectFrontURL+"/home");
        }

        //테스트 url
//        response.sendRedirect("http://3.36.123.194:8081/users/welcome");
        log.info("로그인 성공! 쿠키에 JWT 저장");
    }

    //쿠키 만들기
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        //쿠키가 살아있을 시간
        cookie.setMaxAge(60*60*60);
        //https 통신에서만 사용 가능
        //cookie.setSecure(true);
        //쿠키가 보일 위치: 전역
        cookie.setPath("/");
        //자바스크립트가 해당 쿠키를 가져가지 못하게 설정
        cookie.setHttpOnly(true);

        return cookie;
    }
}