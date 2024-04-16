package com.caffeinedoctor.userservice.security.oauth2;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.info("exception={}", exception.getMessage());

        // Authorization 쿠키 삭제
        Cookie authCookie = WebUtils.getCookie(request, "Authorization");
        if (authCookie != null) {
            authCookie.setMaxAge(0);
            response.addCookie(authCookie);
        }

        // 에러 메시지와 함께 리다이렉션할 URI 생성
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/")
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();

        // targetUrl로 리다이렉션
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}