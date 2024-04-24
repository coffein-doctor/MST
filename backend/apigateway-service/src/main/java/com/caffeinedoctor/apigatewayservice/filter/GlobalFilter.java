package com.caffeinedoctor.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

// 공통 필터 (모든 서비스에 자동 적용)
// Custom 필터와 적용 방법은 비슷
@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter() {
        // 생성자
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filter
        return (exchange, chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("Global Filter baseMessage: -> {}", config.getBaseMessage());

            if(config.isPreLogger()) {
                log.info("Global Filter Start: request id -> {}", request.getId());
            }
            //Custom Post Filter (Mono는 Spring 5 WebFlux에서 제공)
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPostLogger()) {
                    log.info("Global Filter End: response status code -> {}", response.getStatusCode());
                }
            }));
        };
    }

    @Data
    public static class Config{
        // yml 파일에서 등록한다.
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
        // boolean 타입은 함수를 만들 때 is~로 시작한다.
    }
}