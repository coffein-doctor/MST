package com.caffeinedoctor.eurekaserver.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// 특정 경로의 접근 막기
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 암호화 시키기
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
    // 특정 경로에 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 무시
        http.csrf((auth) -> auth.disable());
        // 모든 경로에 권한 요구
        http.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated());
        // http 베이직 방식으로 로그인
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 서버 접속 아이디, 비밀번호 등록
    @Bean
    public UserDetailsService userDetailsService() {
        // user1 사용자 생성
        UserDetails user1 = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("mst0117"))
                .roles("ADMIN")
                .build();

        // 인 메모리 방식으로 저장
        return new InMemoryUserDetailsManager(user1);
    }
}