package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.response.user.KakaoLoginResponseDto;
import com.caffeinedoctor.userservice.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OAuth", description = "OAuth API")
@RestController
@RequestMapping("/oauth/callback")
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class OAuthController {

    private final OAuthService kakaoOAuthService;

    // 카카오 로그인 인가 코드 받기

    @Operation(
            summary = "카카오 로그인",
            description = "카카오 로그인 redirect-uri 입니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "카카오 로그인에 성공하였습니다."
    )
    @GetMapping("/kakao")
//    @Description("회원이 카카오 소셜 로그인을 마치면 자동으로 실행되는 API입니다. 인가 코드를 이용해 토큰을 받고, 해당 토큰으로 사용자 정보를 조회합니다." +
//            "사용자 정보를 이용하여 서비스에 회원가입합니다.")
    public ResponseEntity<KakaoLoginResponseDto> kakaoCallback(@RequestParam("code") String code) {
        // Kakao 토큰 요청
        String accessToken = kakaoOAuthService.requestKakaoToken(code);
        // Kakao 사용자 정보 요청
        KakaoLoginResponseDto userInfo = kakaoOAuthService.requestKakaoUserInfo(accessToken);

        // Kakao 사용자 정보 요청의 상태 코드와 사용자 정보를 바디로 반환합니다.
        return ResponseEntity.ok(userInfo);
    }


//    @GetMapping("/callback")
//    public @ResponseBody String kakaoCallback(String code) {
//        // 토큰 발급 요청
//        // post 방식으로 key=value 데이터 요청
//        RestTemplate rt = new RestTemplate();
//
//        // HttpHeader 오브젝트 생성
//        HttpHeaders headers = new HttpHeaders();
//        // 헤더
//        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HttpBody 오브젝트 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", "4c2e4d4852cafb55e5d18db0521ecee3");
//        params.add("redirect_uri", "http://localhost:8081/auth/kakao/callback");
//        params.add("code", code);
//
//        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기 (exchange에는 HttpEntity 오브젝트를 넣어야하기 때문)
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
//
//        // Post방식으로 Http요청하기 -> response 변수의 응답을 받음.
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token", // 요청 주소
//                HttpMethod.POST, // 요청 메서드
//                kakaoTokenRequest, // 보낼 데이터 (헤더와 바디 값)
//                String.class // 응답 받을 타입
//        );
//        return "카카오 토큰 요청 완료: 토큰 요청애 대한 응답: " + response;
////        return "카카오 로그인 인증 완료: 코드값: " + code;
//    }
}
