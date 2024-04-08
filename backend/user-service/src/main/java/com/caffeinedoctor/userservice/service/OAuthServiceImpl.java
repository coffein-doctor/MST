package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.enums.UserType;
import com.caffeinedoctor.userservice.dto.response.user.KakaoOAuthTokenResponseDto;
import com.caffeinedoctor.userservice.dto.response.user.KakaoUserInfoResponseDto;
import com.caffeinedoctor.userservice.dto.response.user.KakaoLoginResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional(readOnly = true) // (성능 최적화 - 읽기 전용에만 사용)
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class OAuthServiceImpl implements OAuthService {

    private final UserService userService;

//    @Value("${kakao.auth-url}")
//    private String kakaoAuthUrl;
//
//    @Value("${kakao.user-api-url}")
//    private String kakaoUserApiUrl;
//
//    @Value("${kakao.restapi-key}")
//    private String restapiKey;
//
//    @Value("${kakao.redirect-url}")
//    private String redirectUrl;


    // 토큰 발급 요청
    @Override
    public String requestKakaoToken(String code) {
        // post 방식으로 key=value 데이터 요청
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        // 헤더
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "4c2e4d4852cafb55e5d18db0521ecee3");
        params.add("redirect_uri", "http://localhost:8081/oauth/callback/kakao");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기 (exchange에는 HttpEntity 오브젝트를 넣어야하기 때문)
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Post방식으로 Http요청하기 -> response 변수의 응답을 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token", // 요청 주소
                HttpMethod.POST, // 요청 메서드
                kakaoTokenRequest, // 보낼 데이터 (헤더와 바디 값)
                String.class // 응답 받을 타입
        );

        // Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoOAuthTokenResponseDto oauthToken = null;

        log.info(response.getBody());

        try {
            oauthToken = objectMapper.readValue(response.getBody(), KakaoOAuthTokenResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return oauthToken.getAccessToken();
//        return "카카오 토큰 요청 완료: 토큰 요청애 대한 응답: " + response.getBody();
//        return "카카오 로그인 인증 완료: 코드값: " + code;
    }

    @Override
    public KakaoLoginResponseDto requestKakaoUserInfo(String accessToken) {
        // post 방식으로 key=value 데이터 요청
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        // 헤더
        headers.add("Authorization", "Bearer "+ accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기 (exchange에는 HttpEntity 오브젝트를 넣어야하기 때문)
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        // Post방식으로 Http요청하기 -> response 변수의 응답을 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me", // 요청 주소
                HttpMethod.POST, // 요청 메서드
                kakaoUserInfoRequest, // 보낼 데이터 (헤더와 바디 값)
                String.class // 응답 받을 타입
        );

        log.info(response.getBody());
        // Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoUserInfoResponseDto kakaoUserInfo = null;

        try {
            kakaoUserInfo = objectMapper.readValue(response.getBody(), KakaoUserInfoResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info(kakaoUserInfo.getKakaoAccount().getEmail());
        log.info(kakaoUserInfo.getConnectedAt());
        // 신규인지, 기존회원인지 체크
        UserType userType = userService.checkUserTypeByEmail(kakaoUserInfo.getKakaoAccount().getEmail());

        KakaoLoginResponseDto userDto = KakaoLoginResponseDto.builder()
                .kakaoId(kakaoUserInfo.getId())
                .userType(userType)
                .connectedAt(kakaoUserInfo.getConnectedAt())
                .email(kakaoUserInfo.getKakaoAccount().getEmail())
                .profileImageUrl(kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl())
                .build();

        return userDto;
    }

}

