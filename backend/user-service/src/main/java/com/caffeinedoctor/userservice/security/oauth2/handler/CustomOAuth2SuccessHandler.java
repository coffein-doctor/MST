package com.caffeinedoctor.userservice.security.oauth2.handler;

import com.caffeinedoctor.userservice.common.util.CookieUtil;
import com.caffeinedoctor.userservice.repository.RefreshRepository;
import com.caffeinedoctor.userservice.security.oauth2.dto.CustomOAuth2User;
import com.caffeinedoctor.userservice.security.jwt.JWTUtil;
import com.caffeinedoctor.userservice.service.TokenService;
import com.caffeinedoctor.userservice.service.UserService;
import jakarta.servlet.ServletException;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

//로그인 성공 핸들러
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final TokenService tokenService;
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

        //1.기존의 리프레쉬 토큰 삭제
        tokenService.deleteExistingRefreshTokens(username);

        //2.새로운 토큰 생성
        //토큰 2개 생성 (jwt 만들기 - 유저이름, 역할, 토큰이 살아있는 시간)
        //10분
        String access = jwtUtil.createJwt("access", username, role, 600000L);
        //24시간
        String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //3.새로운 리프레쉬 토큰 저장
        //Refresh 토큰 저장
        tokenService.addRefreshEntity(username, refresh, 86400000L);

        //응답 설정

        // <refresh 토큰 설정>
        //쿠키에 넣기
        response.addCookie(CookieUtil.createRefreshCookie("refresh", refresh));
        //상태 코드: 200 응답 보내기
        response.setStatus(HttpStatus.OK.value());

        // Check user status
//        UserStatus userStatus = userService.getUserStatusByUsername(username);
//        if (userStatus == UserStatus.NEW_USER) {
//
//            // accessToken을 쿼리스트링에 담는 url을 만들어준다.
//            String targetUrl = UriComponentsBuilder.fromUriString(redirectFrontURL)
//                    .queryParam("access", access)
//                    .build()
//                    .encode(StandardCharsets.UTF_8)
//                    .toUriString();
//
//            // Redirect to signup page for new users
//            response.sendRedirect(redirectFrontURL+"/signup");
////            getRedirectStrategy().sendRedirect(request, response, redirectFrontURL+"/signup");
//        } else {
//            // Redirect to home page for existing users
//            response.sendRedirect(redirectFrontURL+"/home");
////            getRedirectStrategy().sendRedirect(request, response,redirectFrontURL+"/home");
//        }

        // <access 토큰 설정>
        //1.헤더에 넣기
        // response.setHeader("access", access);
        //2.쿠키에 넣기
        //response.addCookie(CookieUtil.createAccessCookie("access", access));

        //3.쿼리스트링에 담는 url을 만들어준다.
        String targetUrl = UriComponentsBuilder.fromUriString(redirectFrontURL)
                .queryParam("access", access)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        // 로그인 확인 페이지로 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

}