package com.caffeinedoctor.userservice.service;

public interface OAuthUserService {

    // 토큰 발급 요청
    String requestKakaoToken(String code);
    // 유저 정보 요청
    String requestKakaoUserInfo(String accessToken);
}
