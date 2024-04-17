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
                .allowedOrigins("http://localhost:3000") // 명확히 허용할 출처 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // OPTIONS 메서드 포함
                .allowedHeaders("*")
                .allowCredentials(true) // 쿠키 및 인증 정보 포함 허용
                .exposedHeaders("Set-Cookie"); // 클라이언트에서 쿠키를 읽을 수 있도록 설정
//        corsRegistry.addMapping("/**")
//                .exposedHeaders("Set-Cookie") //쿠키 헤더로 전달
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
////                .allowCredentials(true)
////                .allowedOrigins("http://localhost:3000") //프론트 서버 주소만 허용
//        ;
    }
}