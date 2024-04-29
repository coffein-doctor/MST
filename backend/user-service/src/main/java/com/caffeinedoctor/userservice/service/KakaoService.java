package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.oauth.KakaoLoginResponseDto;

public interface KakaoService {

    // 토큰 발급 요청
    String requestKakaoToken(String code);
    // 유저 정보 요청
    KakaoLoginResponseDto requestKakaoUserInfo(String accessToken);
}
