package com.caffeinedoctor.userservice.security.oauth2.dto;

public interface OAuth2Response {

    //제공자 (Ex.kakao, naver, google, ...)
    String getProvider();
    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();
    //사용자 이메일
    String getEmail();
    //시용자 프로필 이미지
    String getProfileImageUrl();

}