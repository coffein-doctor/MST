package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.socialLoginDto;
import com.caffeinedoctor.userservice.dto.response.oauth2.CustomOAuth2User;
import com.caffeinedoctor.userservice.dto.response.oauth2.KakaoOAuth2Response;
import com.caffeinedoctor.userservice.dto.response.oauth2.OAuth2Response;
import com.caffeinedoctor.userservice.dto.response.oauth2.OAuth2UserResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static com.caffeinedoctor.userservice.enums.UserStatus.EXISTING_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserServiceImpl extends DefaultOAuth2UserService {
    // DefaultOAuth2UserService가 유저 정보를 획득하기위한 함수들을 가지고있다.

    private final UserService userService;

    // 카카오 사용자 정보를 외부 인자로 받아온다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2User 객체로 받아옴
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            // 내부 정보 출력
            log.info(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        boolean userExistsByEmail = userService.isUserExistsByEmail(oAuth2Response.getEmail());
        // username을 사용하여 사용자의 존재 여부 확인
        boolean userExistsByUsername = userService.isUserExistsByUsername(username);

        log.info(oAuth2Response.getEmail());
        log.info(username);

        // 이메일과 유저아이디로 두번 검증
        if (userExistsByEmail && userExistsByUsername) {
            //기존 회원인 경우
            log.info("EXISTING_USER");
            // 프로필 이미지, 로그인 시간 갱신
            userService.socialLogin(oAuth2Response.getEmail(), oAuth2Response.getProfileImageUrl());

        } else {
            //신규 회원인 경우
            log.info("NEW_USER");
            socialLoginDto socialLoginSignUpDto = socialLoginDto.builder()
                    .email(oAuth2Response.getEmail())
                    .username(username)
                    .profileImageUrl(oAuth2Response.getProfileImageUrl())
                    .build();
            //DB에 소셜로그인 기본 정보 저장
            userService.socialLoginSignUp(socialLoginSignUpDto);

        }

        OAuth2UserResponseDto userDto = OAuth2UserResponseDto.builder()
                .role("ROLE_USER")
                .username(username)
                .email(oAuth2Response.getEmail())
                .profileImageUrl(oAuth2Response.getProfileImageUrl())
                .build();


        return new CustomOAuth2User(userDto);
    }
}