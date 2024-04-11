package com.caffeinedoctor.userservice.security.oauth2;

import com.caffeinedoctor.userservice.dto.response.oauth2.CustomOAuth2User;
import com.caffeinedoctor.userservice.security.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    // 로그인이 성공하면 동작

    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        //username
        String username = customUserDetails.getName();
        //role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        //jwt 만들기 (유저이름, 역할, 토큰이 살아있는 시간) : 60초 * 60분 * 60시간 = 2.5일
        String token = jwtUtil.createJwt(username, role, 60*60*60L);
        //쿠키 방식으로 토큰 전달
        response.addCookie(createCookie("Authorization", token));
        //프론트 특정 url 넣기
        response.sendRedirect("http://localhost:3000/");
    }

    //쿠키 만들기
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        // 쿠키가 살아있을 시간
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