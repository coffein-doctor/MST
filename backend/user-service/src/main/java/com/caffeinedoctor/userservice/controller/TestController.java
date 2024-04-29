package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.GreetingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test", description = "Backend Test API")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final Environment env;
    private final GreetingDto greeting;

    // 작동 상태 체크
    @Operation(
            summary = "Port 번호 조회",
            description = "User Service의 랜덤 Port 번호를 조회 합니다."
    )
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @Operation(
            summary = "Welcome",
            description = "Welcome to the MST Project."
    )
    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();

    }

    @Operation(
            summary = "API GATEWAY 필터를 통한 토큰 정보 사용",
            description = "토큰 정보 사용하기"
    )
    @GetMapping("/customHeader")
    public ResponseEntity<String> getCustomHeader(HttpServletRequest request) {
        String username = request.getHeader("X-Username");

        if (username == null || username.isEmpty()) {
            log.error("X-Username가 없습니다.");
            return ResponseEntity.badRequest().body("X-Username가 없습니다.");
        } else {
            log.debug("X-Username 정보: " + username);
            return ResponseEntity.ok("X-Username 정보: " + username);
        }
    }
}

