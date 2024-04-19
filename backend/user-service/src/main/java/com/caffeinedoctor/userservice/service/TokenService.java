package com.caffeinedoctor.userservice.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface TokenService {

    /** 리프레쉬 토큰으로 A**/
    ResponseEntity<String> reissueToken(HttpServletRequest request, HttpServletResponse response);
    /** 토큰 지우기 **/
    ResponseEntity<String> removeToken(HttpServletRequest request, HttpServletResponse response);
}
