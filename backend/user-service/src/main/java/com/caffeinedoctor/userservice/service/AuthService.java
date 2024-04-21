package com.caffeinedoctor.userservice.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface TokenService {

    /** 리프레쉬 토큰으로 A**/
    ResponseEntity<String> reissueToken(HttpServletRequest request, HttpServletResponse response);
    /** 토큰 삭제 - 만료시키기 **/
    ResponseEntity<String> removeToken(HttpServletRequest request, HttpServletResponse response);

    /** 토큰 저장 **/
    void addRefreshEntity(String username, String newRefreshToken, Long expiredMs);

    /** username이 같은 모든 토큰 지우기 **/
    void deleteExistingRefreshTokens(String username);
}