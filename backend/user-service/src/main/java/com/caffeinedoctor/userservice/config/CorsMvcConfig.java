package com.caffeinedoctor.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        //모든 경로에 매핑
        corsRegistry.addMapping("/**")
                .exposedHeaders("Set-Cookie") //쿠키 헤더로 전달
                .allowedOrigins("http://localhost:63342") //프론트 서버 주소만 허용
                .allowedOrigins("http://localhost:3000") //프론트 서버 주소만 허용
                .allowedOrigins("http://localhost:8081") //프론트 서버 주소만 허용
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .allowCredentials(true)
        ;
    }
}