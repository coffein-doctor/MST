package com.caffeinedoctor.apigatewayservice.handler;

import com.caffeinedoctor.apigatewayservice.util.WebErrorUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//@Order(-2)
//public class AuthorizationHeaderExceptionHandler implements WebExceptionHandler {
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        HttpStatus status = HttpStatus.UNAUTHORIZED;
//        String errorMessage = "인증에 실패했습니다.";
//        int errorCode = 401;
//
//        if (ex instanceof ExpiredJwtException) {
//            errorMessage = "토큰이 만료되었습니다.";
//            errorCode = 4011;
//        } else if (ex instanceof MalformedJwtException) {
//            errorMessage = "잘못된 형식의 토큰입니다.";
//            errorCode = 4012;
//        } else if (ex instanceof SignatureException) {
//            errorMessage = "잘못된 서명의 토큰입니다.";
//            errorCode = 4013;
//        } else if (ex instanceof UnsupportedJwtException) {
//            errorMessage = "지원되지 않는 JWT 토큰입니다.";
//            errorCode = 4014;
//        } else if (ex instanceof NullPointerException) {
//            errorMessage = "토큰이 입력되지 않았습니다.";
//            errorCode = 4015;
//        }
//
//        return WebErrorUtils.onError(exchange, status, errorCode, errorMessage);
//    }
//}
