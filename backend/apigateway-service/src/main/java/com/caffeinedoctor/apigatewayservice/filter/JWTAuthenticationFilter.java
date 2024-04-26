package com.caffeinedoctor.apigatewayservice.filter;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
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

//            // 인증 필요없는 API는 따로 처리: 필터 적용 X
//            if (accessToken == null) {
//                log.info("API GATEWAY JWT 인증 실패: Access 토큰 없음");
//                // 토큰이 없는 경우, 401 Unauthorized 반환
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }


            jwtUtil.isExpired(accessToken);
            // 유효한 토큰인지 확인
            try {
                // 토큰 검증
                jwtUtil.isExpired(accessToken);
            } catch (ExpiredJwtException e) {
                log.info("API GATEWAY JWT 인증 실패: Access 토큰 만료됨");
                // 검증 실패 시 처리
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            // access 토큰인지 확인
            String category = jwtUtil.getCategory(accessToken);
            if (!"access".equals(category)) {
                log.info("API GATEWAY JWT 인증 실패: Access 토큰이 아님");
                // 검증 실패 시 처리
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // 추가 로직
            // username과 role 정보를 이용하여 필요한 처리를 할 수 있다.
            String username = jwtUtil.getUsername(accessToken);
            // String role = jwtUtil.getRole(accessToken);
            // 요청 헤더에 추가하는 방법
            exchange.getRequest().mutate()
                    .headers(httpHeaders -> httpHeaders.add("X-Auth-Username", username)).build();

            log.info("API GATEWAY: Access 토큰 검증 완료");
            // 검증 성공 시, 체인을 계속 진행
            return chain.filter(exchange);
        };
    }
    @Bean
    public ErrorWebExceptionHandler tokenValidation() {
        return new JwtTokenExceptionHandler();
    }

    public class JwtTokenExceptionHandler implements ErrorWebExceptionHandler {

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
                errorCode = 401;
                status = HttpStatus.UNAUTHORIZED;
                errorMessage = "토큰이 비어있습니다.";
            } else if (ex instanceof ExpiredJwtException) {
                log.error("토큰이 만료되었습니다.");
                errorCode = 402;
                status = HttpStatus.PAYMENT_REQUIRED;
                errorMessage = "토큰이 만료되었습니다.";
            } else if (ex instanceof MalformedJwtException) {
                log.error("JWT 토큰 구조가 잘못되었습니다.");
                errorCode = 403;
                status = HttpStatus.UNAUTHORIZED;
                errorMessage = "JWT 토큰 구조가 잘못되었습니다.";
            } else if (ex instanceof SignatureException) {
                log.error("변조된 토큰입니다.");
                errorCode = 404;
                status = HttpStatus.UNAUTHORIZED;
                errorMessage = "변조된 토큰입니다.";
            } else if (ex instanceof UnsupportedJwtException) {
                log.error("JWT 형식이 잘못되었습니다.");
                errorCode = 405;
                status = HttpStatus.UNAUTHORIZED;
                errorMessage = "JWT 형식이 잘못되었습니다.";
            }

            exchange.getResponse().setStatusCode(status);
            String body = String.format("{\"errorCode\": %d, \"errorMessage\": \"%s\"}", errorCode, errorMessage);
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }
    }
}
