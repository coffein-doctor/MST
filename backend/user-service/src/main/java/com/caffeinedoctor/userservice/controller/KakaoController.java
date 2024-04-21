package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.response.oauth.KakaoLoginResponseDto;
import com.caffeinedoctor.userservice.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OAuth", description = "OAuth API")
//@RestController
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
}
