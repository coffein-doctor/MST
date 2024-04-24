package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.TokenStatusDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface TokenService {

    /** 리프레쉬 토큰으로 A**/
    TokenStatusDto reissueToken(HttpServletRequest request, HttpServletResponse response);
    /** 토큰 삭제 - 만료시키기 **/
    // 로그아웃
    TokenStatusDto removeToken(HttpServletRequest request, HttpServletResponse response);
    // 회원탈퇴
    TokenStatusDto removeAllToken(HttpServletRequest request, HttpServletResponse response, String username);

    /** 토큰 저장 **/
    void addRefreshEntity(String username, String newRefreshToken, Long expiredMs);

    /** username이 같은 모든 토큰 지우기 **/
    void deleteExistingRefreshTokens(String username);
}
