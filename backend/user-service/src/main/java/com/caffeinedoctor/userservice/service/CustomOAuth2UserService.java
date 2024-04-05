package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.common.enums.UserType;
import com.caffeinedoctor.userservice.dto.response.oauth2.CustomOAuth2User;
import com.caffeinedoctor.userservice.dto.response.oauth2.KakaoOAuth2Response;
import com.caffeinedoctor.userservice.dto.response.oauth2.OAuth2Response;
import com.caffeinedoctor.userservice.dto.response.oauth2.OAuth2UserResponseDto;
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

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info(String.valueOf(oAuth2User));

        // 지금은 카카오 밖에 없음.
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
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        // 신규인지, 기존회원인지 체크
        UserType userType = userService.checkUserTypeByEmail(oAuth2Response.getEmail());

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