package com.caffeinedoctor.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user-service/**")
                        .filters(f -> f.addRequestHeader("user-request", "user-request-header")
                                .addResponseHeader("user-response", "user-response-header"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/beverage-service/**")
                        .filters(f -> f.addRequestHeader("beverage-request", "beverage-request-header")
                                .addResponseHeader("beverage-response", "beverage-response-header"))
                        .uri("http://localhost:8082"))
                .route(r -> r.path("/community-service/**")
                        .filters(f -> f.addRequestHeader("community-request", "community-request-header")
                                .addResponseHeader("community-response", "community-response-header"))
                        .uri("http://localhost:8083"))
                .build();
    }
}