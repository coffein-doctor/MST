package com.caffeinedoctor.apigatewayservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "MST API 명세서",
                description = "MST 서비스 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userServiceApi() {
        return GroupedOpenApi.builder()
                .group("user-service")
                .pathsToMatch("/user-service/**")
//                .pathsToMatch("/users/**", "/token/**", "/oauth2/**")
                .build();
    }

    @Bean
    public GroupedOpenApi beverageServiceApi() {
        return GroupedOpenApi.builder()
                .group("beverage-service")
                .pathsToMatch("/beverage-service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi communityServiceApi() {
        return GroupedOpenApi.builder()
                .group("community-service")
                .pathsToMatch("/community-service/**")
                .build();
    }

}
