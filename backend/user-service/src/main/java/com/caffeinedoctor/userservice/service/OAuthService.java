package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.KakaoUserInfoResponseDto;
import com.caffeinedoctor.userservice.dto.response.UserResponseDto;

public interface OAuthService {

    // 토큰 발급 요청
    String requestKakaoToken(String code);
    // 유저 정보 요청
    UserResponseDto requestKakaoUserInfo(String accessToken);
}
