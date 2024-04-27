package com.caffeinedoctor.apigatewayservice.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
public class JWTTokenExceptionHandler implements ErrorWebExceptionHandler {

    private String getErrorJson(int errorCode, String errorMessage) {
        return String.format("{\"errorCode\": %d, \"errorMessage\": \"%s\"}", errorCode, errorMessage);
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        String errorMessage = "서비스가 연결되지 않았습니다.";
        int errorCode = 503;

        if (ex instanceof NullPointerException) {
            log.error("토큰이 비어있습니다.");
            errorCode = 4001;
            status = HttpStatus.UNAUTHORIZED;
            errorMessage = "토큰이 비어있습니다.";
        } else if (ex instanceof ExpiredJwtException) {
            log.error("토큰이 만료되었습니다.");
            errorCode = 4002;
            status = HttpStatus.UNAUTHORIZED;
            errorMessage = "토큰이 만료되었습니다.";
        } else if (ex instanceof MalformedJwtException) {
            log.error("JWT 토큰 구조가 잘못되었습니다.");
            errorCode = 4003;
            status = HttpStatus.UNAUTHORIZED;
            errorMessage = "JWT 토큰 구조가 잘못되었습니다.";
        } else if (ex instanceof SignatureException) {
            log.error("변조된 토큰입니다.");
            errorCode = 4004;
            status = HttpStatus.UNAUTHORIZED;
            errorMessage = "변조된 토큰입니다.";
        } else if (ex instanceof UnsupportedJwtException) {
            log.error("JWT 형식이 잘못되었습니다.");
            errorCode = 4005;
            status = HttpStatus.UNAUTHORIZED;
            errorMessage = "JWT 형식이 잘못되었습니다.";
        }

        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        exchange.getResponse().setStatusCode(status);
        String errorMessageDto = getErrorJson(errorCode, errorMessage);
        byte[] bytes = errorMessageDto.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}