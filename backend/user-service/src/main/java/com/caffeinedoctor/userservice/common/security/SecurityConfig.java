package com.caffeinedoctor.userservice.common.security;


import com.caffeinedoctor.userservice.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

// 시큐리티 커스텀 config를 작성하여 특정 경로에 대해 접근을 막는다.
@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    // 허용 주소
    private static final String[] WHITE_LIST = {
            "/", "/**",
            "/users/**",
            "/login/**", "/oauth/**", "/oauth2/**",
            "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs", "/api-docs/**"
    };

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // 특정 경로에 대해서 보안 설정하기
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // 비활성화
        http
                .formLogin(AbstractHttpConfigurer::disable) //From 로그인 방식 disable - OAuth2 방식
                .httpBasic(AbstractHttpConfigurer::disable) //HTTP Basic 인증 방식 disable - OAuth2 방식
                .csrf(AbstractHttpConfigurer::disable)  // CSRF 보안 disable - JWT 방식으로 관리
                .headers(
                        headersConfigurer ->
                                headersConfigurer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::disable
                                        )
                );  // frameOptions 비활성화

        //세션 설정 : STATELESS - 사용 안함
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //경로별 인가 작업 - URL별 권한 관리 옵션: 모든 경로에 대해 권한이 필요하게 한다.
        http
                .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(WHITE_LIST).permitAll()
                    // 모든 요청에 대해 접근 제어 -> 주어진 IP 주소로부터의 요청만을 허용 (내 IP)
                    .requestMatchers("/**").access(
                            new WebExpressionAuthorizationManager("hasIpAddress('127.0.0.1') or hasIpAddress('3.36.123.194')"))
                    .anyRequest().authenticated() // 위를 제외한 다른 요청은 모두 인증해야돼
//                    .requestMatchers("/", "/oauth2/**", "/user").permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll()
//                    .requestMatchers("/**").access(this::hasIpAddress)
        );

        //oauth2
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)));

        return http.build();
    }


}