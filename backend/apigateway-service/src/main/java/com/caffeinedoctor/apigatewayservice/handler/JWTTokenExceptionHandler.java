package com.caffeinedoctor.apigatewayservice.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
public class JWTTokenExceptionHandler implements WebExceptionHandler {

    private String getErrorCode(int errorCode) {
        return "{\"errorCode\":" + errorCode + "}";
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        int errorCode = 503;
        if (ex instanceof NullPointerException) {
            log.error("토큰이 비어있습니다.");
            errorCode = 401;
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof ExpiredJwtException) {
            log.error("토큰이 만료되었습니다.");
            errorCode = 402;
            exchange.getResponse().setStatusCode(HttpStatus.PAYMENT_REQUIRED);
        } else if (ex instanceof MalformedJwtException) {
            log.error("JWT 토큰 구조가 잘못되었습니다.");
            errorCode = 403;
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof SignatureException) {
            log.error("변조된 토큰입니다.");
            errorCode = 404;
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof UnsupportedJwtException) {
            log.error("JWT 형식이 잘못되었습니다.");
            errorCode = 405;
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        } else {
            log.error("서비스가 연결되지 않았습니다.");
            exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        }

        byte[] bytes = getErrorCode(errorCode).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}