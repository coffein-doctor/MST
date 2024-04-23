package com.caffeinedoctor.userservice.security.oauth2.handler;

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
        if (response.isCommitted()) {
            // 응답이 이미 커밋되었으면 추가 작업을 수행하지 않고 종료합니다.
            return;
        }

        // Authorization 쿠키 삭제
        Cookie authCookie = WebUtils.getCookie(request, "refresh");
        if (authCookie != null) {
            // 쿠키의 만료 시간을 0으로 설정하여 쿠키를 즉시 만료시킵니다.
            authCookie.setMaxAge(0);
            // 클라이언트에게 변경된 쿠키를 전달하여 클라이언트 측에서 해당 쿠키를 삭제하도록 합니다.
            response.addCookie(authCookie);
            log.info("쿠키 만료 시간: " + authCookie.getMaxAge());
        }

        // 인증 실패에 대한 상세한 메시지 생성
        String errorMessage = "소셜 로그인에 실패했습니다. 원인: " + exception.getLocalizedMessage();

        // 실패 메시지와 함께 리다이렉션할 URI 생성
        String redirectUri = UriComponentsBuilder.fromUriString("http://localhost:3000/")
                .queryParam("error", errorMessage)
                .build().toUriString();

        // 인증 실패에 대한 상세한 로그 생성
        log.error("인증 실패: " + errorMessage);

        // 클라이언트를 리다이렉션합니다.
        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }
}