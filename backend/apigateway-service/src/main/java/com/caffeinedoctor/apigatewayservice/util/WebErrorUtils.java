package com.caffeinedoctor.apigatewayservice.util;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class WebErrorUtils {

    private static final String ERROR_TEMPLATE = "{\"errorCode\": %d, \"errorMessage\": \"%s\"}";

    public static Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus, int errorCode, String errorMessage) {
        exchange.getResponse().setStatusCode(httpStatus);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String errorJson = String.format(ERROR_TEMPLATE, errorCode, errorMessage);
        byte[] bytes = errorJson.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}