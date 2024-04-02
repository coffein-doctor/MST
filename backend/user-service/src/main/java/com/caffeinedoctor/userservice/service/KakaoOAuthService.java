package com.caffeinedoctor.userservice.service;

public interface KakaoOAuthService {

    // 토큰 발급 요청
    String requestKakaoToken(String code);
}
