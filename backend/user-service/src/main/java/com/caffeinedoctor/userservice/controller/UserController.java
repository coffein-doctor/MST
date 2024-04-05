package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.request.user.UserRequestDto;
import com.caffeinedoctor.userservice.service.UserService;
import com.caffeinedoctor.userservice.vo.Greeting;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class UserController {
    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;
    // 생성자 주입
//    @Autowired
//    public UserController(Environment env, Greeting greeting) {
//        this.env = env;
//        this.greeting = greeting;
//    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@Valid @RequestBody UserRequestDto userDto) {
        log.info(String.valueOf(userDto.getBirth()));
        Long userId = userService.signUp(userDto);
        return ResponseEntity.ok(userId);
    }

    // 작동 상태 체크
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();

    }

}
