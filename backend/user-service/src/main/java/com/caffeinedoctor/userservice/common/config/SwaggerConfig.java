package com.caffeinedoctor.userservice.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

//http://localhost:8080/swagger-ui/index.html 접속
@OpenAPIDefinition(
        info = @Info(title = "User-Service API 명세서",
                description = "MST 사용자 서비스 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {


}