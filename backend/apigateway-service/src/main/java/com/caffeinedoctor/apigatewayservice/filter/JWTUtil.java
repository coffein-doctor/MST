package com.caffeinedoctor.apigatewayservice.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

//토큰 만들기 (로그인-인증 과정)
@Component
@Slf4j
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${JWT.TOKEN.SECRET-KEY}") String secret) {

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());

    }

    //토큰 검증 함수

    //유저 이름
    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    //유저 역할
    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    //카테고리 꺼내기
    public String getCategory(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    //토큰 만료 확인
    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {

        try {
            // 유효한 토큰인지 만료 시간을 검증합니다.
            isExpired(token);

            // 위의 코드가 예외 없이 수행되면, 토큰이 유효합니다.
            return true;
        } catch (SignatureException ex) {
            // 서명 검증 실패
            log.error("Invalid JWT signature");
        } catch (ExpiredJwtException ex) {
            // 토큰 만료
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            // 지원되지 않는 JWT 형식
            log.error("Unsupported JWT token");
        } catch (MalformedJwtException ex) {
            // JWT 형식 불일치 (여기서는 카테고리가 잘못되었을 때를 처리합니다.)
        } catch (IllegalArgumentException ex) {
            // 부적절한 토큰
            log.error("JWT claims string is empty");
        } catch (JwtException ex) {
            // 그 외 모든 JWT 예외
            log.error("Error in JWT token");
        }
        return false;
    }

}