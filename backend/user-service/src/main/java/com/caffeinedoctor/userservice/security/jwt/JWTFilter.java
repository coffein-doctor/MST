package com.caffeinedoctor.userservice.security.jwt;

import com.caffeinedoctor.userservice.security.oauth2.dto.CustomOAuth2User;
import com.caffeinedoctor.userservice.security.oauth2.dto.OAuth2UserResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

//토큰 검증 (경로접근-인가 과정)
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    //jwt 검증 로직
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //필터 위치에 따라 OAuth2 인증을 진행하는 필터보다 JWTFilter가 앞에 존재하는 경우 에러 발생
        String requestUri = request.getRequestURI();
        //JWTFilter 내부에 if문을 통해 특정 경로 요청은 넘어가도록 수정
        if (requestUri.matches("^\\/login(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }

        /** Access 토큰 필터 시작 **/
        log.info("Access 토큰 검증 시작");

        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");

        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {
            // 권한이 필요없는 요청도 있기 때문에 doFilter로 넘긴다.
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰이 있다면
        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            // 토큰이 만료되었는 지 확인
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            // Access 토큰이 만료된 경우 특정한 상태 코드 및 메시지를 응답
            log.info("access token expired");

            //response body
            PrintWriter writer = response.getWriter();
            //응답
            writer.print("access token expired");

            //response status code
            //401 응답 코드 -> 프론트에서 만료된 토큰 응답을 받으면 리프레쉬 토큰을 주고 엑세스 토큰 재발급 받을 수 있도록 한다.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // 만료되었으면 doFilter로 넘기지 않는다.
            // 토큰이 만료되었다는 메시지와 함께 응답을 보낸다.
            return;
        }

        // 토큰이 access인지 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);
        //refresh이면 'access아니다.' 메시지와 함께 상태코드 응답을 보낸다.
        if (!category.equals("access")) {
            log.info("invalid access token");

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            //response status code
            //401 응답 코드
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        log.info("Access 토큰 검증 완료");

        // 일시적인 세션 만들기
        // username, role 값을 획득
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //userDTO를 생성하여 값 저장
        OAuth2UserResponseDto userDto = OAuth2UserResponseDto.builder()
                .role(role)
                .username(username)
                .build();
        //UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

        //스프링 시큐리티 로그인 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        //일시적인 세션에 사용자 로그인 인증 토큰 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}