package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OAuth2 소셜 로그인", description = "OAuth2를 사용한 소셜 로그인 API")
@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class OAuth2Controller {

    @Operation(
            summary = "소셜 로그인",
            description = "소셜 로그인 페이지로 이동합니다. 현재는 '카카오 로그인'이 구현되어 있습니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "소셜 로그인 페이지입니다."
    )
    @PostMapping("/authorization/{social}")
    public ResponseEntity<String> socialLogin(@PathVariable String social) {
        // 로직은 해당 소셜 미디어에 대한 로그인 처리를 구현하면 됩니다.
        return ResponseEntity.ok(social + " 로그인을 수행해주세요");
    }
}
