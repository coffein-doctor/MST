package com.caffeinedoctor.userservice.security.oauth2.dto;

import lombok.RequiredArgsConstructor;

import java.util.Map;

// 카카오 응답 형태에 따라 생성
@RequiredArgsConstructor
public class KakaoOAuth2Response implements OAuth2Response {
    // 카카오 유저 데이터를 받을 변수 선언.
    private final Map<String, Object> attribute;

    public Map<String, Object> getKakaoAccount(){
        return (Map<String, Object>) attribute.get("kakao_account");
    }

    public Map<String, Object> getProfile(){
        return (Map<String, Object>) getKakaoAccount().get("profile");
    }

    @Override
    public String getProvider() {

        return "kakao";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {

        return getKakaoAccount().get("email").toString();
    }

    @Override
    public String getProfileImageUrl() {
        return getProfile().get("profile_image_url").toString();
    }
}