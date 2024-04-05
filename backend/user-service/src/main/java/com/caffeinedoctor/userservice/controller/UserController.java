package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.request.user.UserRequestDto;
import com.caffeinedoctor.userservice.service.UserService;
import com.caffeinedoctor.userservice.dto.GreetingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class UserController {
    private final Environment env;
    private final GreetingDto greeting;
    private final UserService userService;
    // 생성자 주입
//    @Autowired
//    public UserController(Environment env, Greeting greeting) {
//        this.env = env;
//        this.greeting = greeting;
//    }
    @Operation(
            summary = "회원가입",
            description = "추가 정보를 입력하여 회원가입을 합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "회원가입에 성공하였습니다."
    )
    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@Valid @RequestBody UserRequestDto userDto) {
        log.info(String.valueOf(userDto.getBirth()));
        Long userId = userService.signUp(userDto);
        return ResponseEntity.ok(userId);
    }

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
