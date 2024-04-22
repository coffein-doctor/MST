package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.request.user.UserInfoRequestDto;
import com.caffeinedoctor.userservice.security.oauth2.dto.CustomOAuth2User;
import com.caffeinedoctor.userservice.dto.response.user.UserDetailsDto;
import com.caffeinedoctor.userservice.enums.UserStatus;
import com.caffeinedoctor.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User 관리 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class UserController {

    private final UserService userService;
    // 생성자 주입
//    @Autowired
//    public UserController(Environment env, Greeting greeting) {
//        this.env = env;
//        this.greeting = greeting;
//    }

    /** 회원 가입 **/
    @Operation(
            summary = "회원 가입을 통해 새로운 사용자 생성",
            description = "추가 등록 정보를 입력하여 새로운 사용자를 생성합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "회원 가입에 성공하였습니다.",
                    content = @Content(
                            schema = @Schema(implementation = Long.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자입니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "사용자를 찾을 수 없습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<?> createUser(@AuthenticationPrincipal CustomOAuth2User oauth2User, @Valid @RequestBody UserInfoRequestDto userDto) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        // 사용자 이름 가져오기
        String username = oauth2User.getName();
        try {
            Long userId = userService.createUser(username, userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User status not found");
        }
    }

    /** 회원 가입 상태 조회 **/
    @Operation(
            summary = "회원 가입 상태 조회",
            description = "소셜 로그인에 성공한 현재 사용자의 회원 가입 상태를 가져옵니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 상태를 가져왔습니다."),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다.")
    })
    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus(@AuthenticationPrincipal CustomOAuth2User oauth2User) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        // 사용자 이름 가져오기
        String username = oauth2User.getName();
        try {
            // 사용자 이름을 사용하여 상태 가져오기
            UserStatus userStatus = userService.getUserStatusByUsername(username);
            return ResponseEntity.ok(userStatus.toString());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User status not found");
        }
    }

    /** 회원 정보 조회 **/
    @Operation(
            summary = "회원 정보 조회",
            description = "해당 회원 id의 회원 정보를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "회원 정보 조회에 성공하였습니다.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailsDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자입니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "해당 ID의 사용자를 찾을 수 없음", content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal CustomOAuth2User oauth2User, @PathVariable Long id) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        try {
            UserDetailsDto userDetailsDto = userService.getUserDetailsById(id);
            return ResponseEntity.ok(userDetailsDto);
        } catch (RuntimeException ex) {
            // RuntimeException 발생 시 예외 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

    /** 회원 정보 수정 **/
    @Operation(
            summary = "회원 정보 수정",
            description = "회원 id와 수정된 정보를 받아와서 회원 정보를 수정합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 정보가 성공적으로 업데이트되었습니다.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailsDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "사용자 인증에 실패하였습니다. 로그인이 필요합니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "해당 작업을 수행할 권리가 없습니다. 로그인된 사용자 Id와 일치하는 사용자 Id가 아닙니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description =  "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@AuthenticationPrincipal CustomOAuth2User oauth2User, @PathVariable Long id, @Valid @RequestBody UserInfoRequestDto userDto) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        // 사용자 이름 가져오기
        String username = oauth2User.getName();
        try {
            UserDetailsDto updatedUser = userService.updateUser(id, username, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (AccessDeniedException e) {
            // 요청한 작업을 수행할 권한이 없을 때 발생하는 예외 처리
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            // 사용자를 찾을 수 없을 때 발생하는 예외 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> hardDeleteUser(@AuthenticationPrincipal CustomOAuth2User oauth2User, @PathVariable Long id) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        return null;
    }

}
