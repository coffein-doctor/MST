package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.GreetingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
}
