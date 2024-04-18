package com.caffeinedoctor.userservice.security;


import com.caffeinedoctor.userservice.config.CorsConfig;
import com.caffeinedoctor.userservice.security.jwt.JWTFilter;
import com.caffeinedoctor.userservice.security.jwt.JWTUtil;
import com.caffeinedoctor.userservice.security.oauth2.handler.CustomOAuth2FailureHandler;
import com.caffeinedoctor.userservice.security.oauth2.handler.CustomOAuth2SuccessHandler;
import com.caffeinedoctor.userservice.security.oauth2.service.CustomOAuth2UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

// 시큐리티 커스텀 config를 작성하여 특정 경로에 대해 접근을 막는다.
@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserServiceImpl customOAuth2UserServiceImpl;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CustomOAuth2FailureHandler customOAuth2FailureHandler;
    private final CorsConfig corsConfig;
    private final JWTUtil jwtUtil;
    private final Environment env;

    // 허용 주소
    private static final String[] WHITE_LIST = {
            "/login/**", "/oauth2/**",
            "/swagger-ui/index.html/**",
            "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs", "/api-docs/**"
    };

//    private static final String[] swaggerURL = {
//            "/api/**", "/graphiql", "/graphql",
//            "/swagger-ui/**", "/swagger-ui.html",
//            "/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html"
//    };


    // 스프링 시큐리티 기능 비활성화 ('인증','인가' 서비스 적용x)
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring()
//                .requestMatchers("/error", "/favicon.ico")
//                .requestMatchers(swaggerURL);
//    }

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    // 특정 경로에 대해서 보안 설정하기
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {


        //jwt를 위한 CORS 설정
        http.cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()));

//        http.
//                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
//
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//
//                        CorsConfiguration configuration = new CorsConfiguration();
//
//                        // 프론트 서버 주소들
//                        configuration.setAllowedOrigins(Arrays.asList("http://localhost:63342", "http://localhost:3000", "http://localhost:8081"));
////                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
////                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8081"));
//                        // get, post, ... 모든 요청에 허용
//                        configuration.setAllowedMethods(Collections.singletonList("*"));
//                        configuration.setAllowCredentials(true);
//                        // 모든 헤더 값 허용
//                        configuration.setAllowedHeaders(Collections.singletonList("*"));
//                        // 쿠키의 유효 기간: 2일
//                        configuration.setMaxAge(172800L);
//                        // 노출 헤더
//                        configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
//                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//
//                        return configuration;
//                    }
//                }));

        // 비활성화
        http
                .formLogin(AbstractHttpConfigurer::disable) //From 로그인 방식 disable - OAuth2 방식
                .httpBasic(AbstractHttpConfigurer::disable) //HTTP Basic 인증 방식 disable - OAuth2 방식
                .csrf(AbstractHttpConfigurer::disable)  // CSRF 보안 disable - JWT 방식으로 관리
//                .cors(AbstractHttpConfigurer::disable)  // CORS 보안 disable
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
                    .anyRequest().authenticated() // 위를 제외한 다른 요청은 모두 인증해야돼
//                    .requestMatchers("/", "/oauth2/**", "/user").permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll()
//                    .requestMatchers("/**").access(this::hasIpAddress)
        );

        //JWTFilter 추가
        http
                .addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);
//                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //oauth2 로그인
        http
                .oauth2Login((oauth2) -> oauth2
                        // 유저 정보 업데이트 및 유저엔티티 테이블 추가
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserServiceImpl))
                        // 카카오 인증 완료, 토근 생성 및 추가 사용자 정보 처리
                        .successHandler(customOAuth2SuccessHandler)
                        //로그인 인증 실패 후 처리
                        .failureHandler(customOAuth2FailureHandler)
                )
                // 인증 되지 않은 url 요청시 처리 프로세스
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .defaultAuthenticationEntryPointFor(
//                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
//                                new AntPathRequestMatcher("/**")
//                        )
//                )
        ;

        return http.build();
    }


}