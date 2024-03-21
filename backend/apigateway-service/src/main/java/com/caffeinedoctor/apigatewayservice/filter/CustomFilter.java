package com.caffeinedoctor.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

// 사용자 정의 필터 (개별적으로 서비스에 따로 등록)
@Component // 일반적인 빈 형태
@Slf4j // 로그파일 출력
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    // AbstractGatewayFilterFactory 상속받기
    public CustomFilter() {
        // 생성자
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filter ex) JWT (람다식으로 표현)
        return (exchange, chain) -> {
            // Netty 비동기 방식 사용 (Spring 5)
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter: request id -> {}", request.getId());

            //Custom Post Filter 반환 값 ex) 에러 (Spring 5 WebFlux에서는 비동기 방식에서 단위 값 전달 MonoDateType 사용 가능 - 데이터타입을 하나만 주겠다)
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("Custom POST filter: response code -> {}", response.getStatusCode());
            }));
        };
    }

    public static class Config{
        // 지금은 특별한 내용이 없다.
        // Put the configuration properties
    }
}
