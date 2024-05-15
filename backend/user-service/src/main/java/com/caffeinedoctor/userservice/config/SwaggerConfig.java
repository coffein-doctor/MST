package com.caffeinedoctor.userservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html 접속
@OpenAPIDefinition(
        info = @Info(title = "User-Service API 명세서",
                description = "MST 사용자 서비스 API 명세서",
                version = "v1"))
@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(@Value("${openapi.service.url}") String url) {
        log.info("serverUrl={}", url);
        return new OpenAPI()
                .servers(List.of(new Server().url(url)));
    }


}