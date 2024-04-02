package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.KakaoOAuthTokenResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
public class KakaoOAuthServiceImpl implements KakaoOAuthService {

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
        params.add("redirect_uri", "http://localhost:8081/auth/kakao/callback");
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

        System.out.println(response.getBody());

        // Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoOAuthTokenResponseDto oauthToken = null;

        try {
            oauthToken = objectMapper.readValue(response.getBody(), KakaoOAuthTokenResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(oauthToken);


        // post 방식으로 key=value 데이터 요청
        RestTemplate rt2 = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        // 헤더
        headers2.add("Authorization", "Bearer "+ oauthToken.getAccess_token());
        headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기 (exchange에는 HttpEntity 오브젝트를 넣어야하기 때문)
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers2);

        // Post방식으로 Http요청하기 -> response 변수의 응답을 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me", // 요청 주소
                HttpMethod.POST, // 요청 메서드
                kakaoUserInfoRequest, // 보낼 데이터 (헤더와 바디 값)
                String.class // 응답 받을 타입
        );

        return response2.getBody();


//        return "카카오 토큰 요청 완료: 토큰 요청애 대한 응답: " + response.getBody();
//        return "카카오 로그인 인증 완료: 코드값: " + code;
    }


}

