package com.caffeinedoctor.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
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
            summary = "소셜 로그인 창으로 연결",
            description = "로그인 경로에 대한 하이퍼링크를 실행하면 소셜 로그인창으로 이동합니다. 현재는 '카카오 로그인'이 구현되어 있습니다."
    )
    @GetMapping("/authorization/{social}")
    public ResponseEntity<Void> socialLogin(@PathVariable String social) {
        // Swagger에 보여주기 위한 API로, 소셜 로그인 경로에 대한 하이퍼링크를 실행하면 소셜 로그인 창으로 이동합니다.
        return ResponseEntity.ok().build();
    }
}
