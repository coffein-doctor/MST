package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.common.enums.UserType;
import com.caffeinedoctor.userservice.dto.response.oauth2.CustomOAuth2User;
import com.caffeinedoctor.userservice.dto.response.oauth2.KakaoOAuth2Response;
import com.caffeinedoctor.userservice.dto.response.oauth2.OAuth2Response;
import com.caffeinedoctor.userservice.dto.response.oauth2.OAuth2UserResponseDto;
import com.caffeinedoctor.userservice.entitiy.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    // DefaultOAuth2UserService가 유저 정보를 획득하기위한 함수들을 가지고있다.

    private final UserService userService;

    // 카카오 사용자 정보를 외부 인자로 받아온다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2User 객체로 받아옴
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 내부 정보 출력
        System.out.println(oAuth2User.getAttributes());
        log.info(oAuth2User.getAttributes().toString());

        // 지금은 카카오 밖에 없지만 각각의 프로바이더 누군지 판단 (카카오, 네이버, 구글, 깃허브 ...)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoOAuth2Response(oAuth2User.getAttributes());
        }
//        else if (registrationId.equals("naver")) {
//
//            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
//        }
//        else if (registrationId.equals("google")) {
//
//            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
//        }
        else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬 - 로그인아이디: kakao_123456789
        String username = oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();

        // 이메일을 사용하여 사용자의 존재 여부 확인
        boolean userExists = userService.isUserExistsByEmail(oAuth2Response.getEmail());
        UserType userType;

        if (userExists) {
            // 기존 회원인 경우
            userType = UserType.EXISTING_MEMBER;
            // 프로필 이미지 갱신
            userService.updateProfileImage(oAuth2Response.getEmail(), oAuth2Response.getProfileImageUrl());

        } else {
            // 신규 회원인 경우
            userType =  UserType.NEW_MEMBER;
        }


        OAuth2UserResponseDto userDto = OAuth2UserResponseDto.builder()
                .role("ROLE_USER")
                .username(username)
                .userType(userType)
                .email(oAuth2Response.getEmail())
                .profileImageUrl(oAuth2Response.getProfileImageUrl())
                .build();

        log.info(username);
        log.info(oAuth2Response.getEmail());


        return new CustomOAuth2User(userDto);
    }
}