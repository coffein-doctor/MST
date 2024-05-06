package com.caffeinedoctor.apigatewayservice.filter;

import com.caffeinedoctor.apigatewayservice.util.JWTUtils;
import com.caffeinedoctor.apigatewayservice.util.WebErrorUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final JWTUtils jwtUtils;

    public AuthorizationHeaderFilter(JWTUtils jwtUtils) {
        super(Config.class);
        this.jwtUtils = jwtUtils;
    }

    private static final String AUTHORIZATION_HEADER = "access";
    private static final String X_USERNAME_HEADER = "X-Username";


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(AUTHORIZATION_HEADER)) {
                return onError(exchange, "토큰이 입력되지 않았습니다.", HttpStatus.UNAUTHORIZED);
//                return WebErrorUtils.onError(exchange, HttpStatus.UNAUTHORIZED, 4005, "토큰이 입력되지 않았습니다.");
            }

            String accessToken = request.getHeaders().getFirst(AUTHORIZATION_HEADER);

            try {
                // 토큰이 만료되었는 지 확인
                jwtUtils.isExpired(accessToken);
            } catch (ExpiredJwtException e) {
                return onError(exchange, "토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED);
//                return WebErrorUtils.onError(exchange, HttpStatus.UNAUTHORIZED, 4001, "토큰이 만료되었습니다.");
            }

            String category = jwtUtils.getCategory(accessToken);
            if (!"access".equals(category)) {
                log.info("Invalid access token category");
                return onError(exchange, "잘못된 형식의 토큰입니다.", HttpStatus.UNAUTHORIZED);
//                return WebErrorUtils.onError(exchange, HttpStatus.UNAUTHORIZED, 4002, "잘못된 형식의 토큰입니다.");
            }

            String username = jwtUtils.getUsername(accessToken);
            exchange.getRequest().mutate()
                    .headers(httpHeaders -> httpHeaders.add(X_USERNAME_HEADER, username)).build();

            return chain.filter(exchange);
        });
    }

//    @Bean
//    public WebExceptionHandler tokenValidation() {
//        return new AuthorizationHeaderExceptionHandler();
//    }
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(err);
        return response.setComplete();
    }

    public static class Config {
        // Put the configuration properties
    }
}
