package com.caffeinedoctor.userservice.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 프론트 서버 주소들
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:63342", "http://localhost:3000", "http://localhost:8081"));
        // get, post, ... 모든 요청에 허용
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
//        corsConfiguration.setAllowCredentials(true);
        // 모든 헤더 값 허용
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        // 쿠키의 유효 기간: 2일
        corsConfiguration.setMaxAge(172800L);
        // 노출 헤더
//        corsConfiguration.setExposedHeaders(Arrays.asList("Set-Cookie", "refresh", "access"));
        corsConfiguration.setExposedHeaders(Collections.singletonList("Set-Cookie"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(
//                Arrays.asList("http://3.36.123.194:8081","http://localhost:3000", "http://localhost:8081", "http://localhost:63342"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Authorization-refresh", "Cache-Control", "Content-Type","refresh-token"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOriginPatterns(
//                Arrays.asList("http://3.36.123.194:8081","http://localhost:3000", "http://localhost:8081", "http://localhost:63342"));
//        configuration.setAllowedMethods(
//                Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setExposedHeaders(
//                Arrays.asList("Content-Type", "X-Requested-With", "Authorization"));
//        configuration.setAllowCredentials(true);
//        //configuration.setMaxAge(3600L); //1시간
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}