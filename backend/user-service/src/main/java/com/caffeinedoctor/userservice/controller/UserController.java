package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.request.user.UserInfoRequestDto;
import com.caffeinedoctor.userservice.dto.response.oauth2.CustomOAuth2User;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.enums.UserStatus;
import com.caffeinedoctor.userservice.service.UserService;
import com.caffeinedoctor.userservice.dto.GreetingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Long> registerUserInfo(@Valid @RequestBody UserInfoRequestDto userDto) {
        log.info(String.valueOf(userDto.getEmail()));
        Long userId = userService.registerUserInfo(userDto);
        return ResponseEntity.ok(userId);
    }

    @Operation(
            summary = "유저의 상태",
            description = "특정 사용자의 상태를 가져옵니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 상태를 가져옴"),
            @ApiResponse(responseCode = "401", description = "인증되지 않음"),
            @ApiResponse(responseCode = "404", description = "사용자 상태를 찾을 수 없음")
    })
    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus(@AuthenticationPrincipal CustomOAuth2User oauth2User) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // 사용자 이름 가져오기
        String username = oauth2User.getName();

        // 사용자 이름을 사용하여 상태 가져오기
        UserStatus userStatus = userService.getUserStatusByUsername(username);

        // 상태가 없는 경우 404 에러 반환
        if (userStatus == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User status not found");
        }

        return ResponseEntity.ok(userStatus.toString());
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
