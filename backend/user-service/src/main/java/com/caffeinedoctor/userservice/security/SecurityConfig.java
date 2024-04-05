package com.caffeinedoctor.userservice.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
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

    // 허용 주소
    private static final String[] WHITE_LIST = {
            "/",
            "/users/**",
            "/**",
            "/oauth2/**",
            "/oauth/**"
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
                .formLogin(AbstractHttpConfigurer::disable) // form 기반 로그인 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // httpBasic 비활성화
                .csrf(AbstractHttpConfigurer::disable)  // CSRF 보안 비활성화
                .headers(
                        headersConfigurer ->
                                headersConfigurer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::disable
                                        )
                );  // frameOptions 비활성화

        // 세션 사용하지 않으므로 STATELESS로 설정 //
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //== URL별 권한 관리 옵션 ==// 모든 경로에 대해 권한이 필요하게 한다.
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(WHITE_LIST).permitAll()
                // 모든 요청에 대해 접근 제어 -> 주어진 IP 주소로부터의 요청만을 허용
                .requestMatchers("/**").access(
                        new WebExpressionAuthorizationManager("hasIpAddress('127.0.0.1') or hasIpAddress('3.36.123.194')"))
                .anyRequest().authenticated() // 위를 제외한 다른 요청은 모두 인증해야돼
//                .requestMatchers("/", "/oauth2/**", "/user").permitAll()
//                .requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll()
//                .requestMatchers("/**").access(this::hasIpAddress)
        );

        return http.build();
    }


}