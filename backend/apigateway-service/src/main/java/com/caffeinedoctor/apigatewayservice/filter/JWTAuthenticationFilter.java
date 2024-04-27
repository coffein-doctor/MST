package com.caffeinedoctor.apigatewayservice.filter;

import com.caffeinedoctor.apigatewayservice.handler.JWTTokenExceptionHandler;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import io.jsonwebtoken.ExpiredJwtException; // ExpiredJwtException 임포트 주의
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JWTAuthenticationFilter extends AbstractGatewayFilterFactory<JWTAuthenticationFilter.Config> {

    private final JWTUtil jwtUtil; // JWT 유틸리티 클래스는 적절히 주입 받거나 정의해야 합니다.

    public JWTAuthenticationFilter(JWTUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {
        // 필터 설정에 사용될 구성 요소들을 정의할 수 있다.
        // 필터 구성을 위한 설정 필드
        // 예: private String someProperty;
    }

//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            // JWT 검증 로직
//            // 예: 헤더에서 JWT 토큰 가져오기, 토큰 검증, 에러 처리 등
//            if (/* 토큰 검증 실패 */) {
//                // 검증 실패 시 처리
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//            // 검증 성공 시, 체인을 계속 진행
//            return chain.filter(exchange);
//        };
//    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("API GATEWAY: JWT Access 토큰 검증");

        return (exchange, chain) -> {
            log.info("API GATEWAY: Access 토큰 검증 시작");
            // 헤더에서 JWT 토큰 가져오기
            String accessToken = exchange.getRequest().getHeaders().getFirst("access");

            // 토큰이 null인지 확인
            if (accessToken == null) {
                log.error("Access token is null.");
                return null;
            }

            // 유효한 토큰인지 확인합니다.
            jwtUtil.validateToken(accessToken);

            // 카테고리가 "access"인지 검증합니다.
            if (!jwtUtil.getCategory(accessToken).equals("access")) {
                log.error("Invalid or missing category in JWT token");
                throw new MalformedJwtException("Invalid or missing category in JWT token"); // MalformedJwtException을 발생시킵니다.
            }

            // 추가 로직
            // username과 role 정보를 이용하여 필요한 처리를 할 수 있다.
            String username = jwtUtil.getUsername(accessToken);
            // String role = jwtUtil.getRole(accessToken);
            // 요청 헤더에 "X-Username" 추가
            exchange.getRequest().mutate()
                    .headers(httpHeaders -> httpHeaders.add("X-Username", username)).build();

            log.info("API GATEWAY: Access 토큰 검증 완료");
            // 검증 성공 시, 체인을 계속 진행
            return chain.filter(exchange);
        };
    }
    @Bean
    public ErrorWebExceptionHandler tokenValidation() {
        return new JWTTokenExceptionHandler();
    }

}
