package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.request.user.UserInfoRequestDto;
import com.caffeinedoctor.userservice.dto.response.FollowDto;
import com.caffeinedoctor.userservice.dto.response.user.SearchUserInfoDto;
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
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            summary = "새로운 사용자 등록",
            description = "회원 가입을 통해 새로운 사용자를 생성합니다. 추가 등록 정보를 입력해주세요."
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
                    description = "사용자 인증에 실패하였습니다. 로그인이 필요합니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<?> createUser(@AuthenticationPrincipal CustomOAuth2User oauth2User,
                                        @Valid @RequestBody UserInfoRequestDto userDto) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증에 실패하였습니다. 로그인이 필요합니다.");
        }
        // 사용자 이름 가져오기
        String username = oauth2User.getName();
        try {
            Long userId = userService.createUser(username, userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /** 회원 Id 조회 **/
    @Operation(
            summary = "회원 Id 조회",
            description = "로그인 회원의 Id를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 Id가 성공적으로 조회되었습니다.",
                    content = @Content(
                            schema = @Schema(implementation = Long.class)
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
                    responseCode = "404",
                    description =  "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<?> getUserId(@AuthenticationPrincipal CustomOAuth2User oauth2User) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증에 실패하였습니다. 로그인이 필요합니다.");
        }

        try {
            // 사용자 이름 가져오기
            String username = oauth2User.getName();
            Long userId = userService.getUserId(username);
            return ResponseEntity.ok(userId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /** 회원 가입 상태 조회 **/
    @Operation(
            summary = "회원 가입 상태 조회",
            description = "소셜 로그인에 성공한 현재 사용자의 회원 가입 상태를 가져옵니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 상태를 가져왔습니다."),
            @ApiResponse(responseCode = "401", description = "사용자 인증에 실패하였습니다. 로그인이 필요합니다."),
            @ApiResponse(responseCode = "404", description = "해당 사용자를 찾을 수 없습니다.")
    })
    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus(@AuthenticationPrincipal CustomOAuth2User oauth2User) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증에 실패하였습니다. 로그인이 필요합니다.");
        }
        // 사용자 이름 가져오기
        String username = oauth2User.getName();
        try {
            // 사용자 이름을 사용하여 상태 가져오기
            UserStatus userStatus = userService.getUserStatusByUsername(username);
            return ResponseEntity.ok(userStatus.toString());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /** 회원 정보 조회 **/
    @Operation(
            summary = "회원 정보 조회",
            description = "특정 회원의 정보를 조회합니다. 회원 ID를 입력하여 해당 회원의 정보를 확인하세요."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 정보가 성공적으로 조회되었습니다.",
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
                    responseCode = "404",
                    description =  "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal CustomOAuth2User oauth2User,
                                            @PathVariable Long userId) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증에 실패하였습니다. 로그인이 필요합니다.");
        }

        try {
            UserDetailsDto userDetailsDto = userService.getUserDetailsById(userId);
            return ResponseEntity.ok(userDetailsDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    /** 회원 정보 수정 **/
    @Operation(
            summary = "회원 정보 수정",
            description = "회원의 정보를 수정합니다. 회원 ID와 수정된 정보를 입력하세요."
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
                    description = "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserDetails(@AuthenticationPrincipal CustomOAuth2User oauth2User,
                                               @PathVariable Long userId,
                                               @Valid @RequestBody UserInfoRequestDto userDto) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증에 실패하였습니다. 로그인이 필요합니다.");
        }
        // 사용자 이름 가져오기
        String username = oauth2User.getName();
        try {
            UserDetailsDto updatedUser = userService.updateUser(userId, username, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (AccessDeniedException e) {
            // 요청한 작업을 수행할 권한이 없을 때 발생하는 예외 처리
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (UsernameNotFoundException e) {
            // 사용자를 찾을 수 없을 때 발생하는 예외 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /** 회원 삭제 **/
    @Operation(
            summary = "회원 탈퇴",
            description = "회원 ID에 해당하는 회원을 탈퇴 처리합니다. 해당 회원의 모든 정보를 삭제합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자가 성공적으로 삭제되었습니다."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Possible reasons: refresh token null, refresh token expired, invalid refresh token"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "사용자 인증에 실패하였습니다. 로그인이 필요합니다."
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "해당 작업을 수행할 권리가 없습니다. 로그인된 사용자 Id와 일치하는 사용자 Id가 아닙니다."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description =  "해당 사용자를 찾을 수 없습니다."
            )
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> hardDeleteUser(HttpServletRequest request, HttpServletResponse response,
                                                 @AuthenticationPrincipal CustomOAuth2User oauth2User,
                                                 @PathVariable Long userId) {
        // 인증된 사용자인지 확인
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증에 실패하였습니다. 로그인이 필요합니다.");
        }

        try {
            // 사용자 이름 가져오기
            String username = oauth2User.getName();
            userService.hardDeleteUser(request, response, userId, username);
            return ResponseEntity.ok().body("사용자가 성공적으로 삭제되었습니다.");
        } catch (AccessDeniedException e) {
            // 요청한 작업을 수행할 권한이 없을 때 발생하는 예외 처리
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (UsernameNotFoundException e) {
            // 사용자를 찾을 수 없을 때의 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            // 토큰 삭제 실패 또는 기타 RuntimeException의 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /** 닉네임 중복 검사 **/
    @Operation(
            summary = "닉네임 중복 검사",
            description = "입력된 닉네임이 이미 사용 중인지 여부를 확인합니다. 닉네임을 입력하여 사용 가능 여부를 확인하세요. 사용가능한 닉네임이면 true를 반환합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "닉네임 사용 가능 여부가 성공적으로 조회되었습니다.",
                    content = @Content(
                            schema = @Schema(implementation = Boolean.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청입니다. 닉네임 파라미터가 누락되었거나 유효하지 않습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        // 닉네임 중복 검사
        boolean isNicknameAvailable  = !userService.isNicknameExists(nickname);
        return ResponseEntity.ok(isNicknameAvailable);
    }

    /** 팔로우 관련 기능 **/
    /** 회원 검색 **/
    @Operation(
            summary = "회원 검색",
            description = "팔로우를 위해 특정 회원을 검색합니다. 회원 닉네임을 입력하여 해당 회원을 검색하세요."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자가 성공적으로 검색되었습니다.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SearchUserInfoDto.class)
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
                    responseCode = "404",
                    description =  "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "회원가입 절차를 완료하지 않은 사용자입니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam String nickname) {

        try {
            SearchUserInfoDto userInfoDto = userService.searchUserByNickname(nickname);
            return ResponseEntity.ok(userInfoDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            // 사용자가 회원가입 절차를 완료하지 않았을 때 422 Unprocessable Entity 반환
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

//     /api/users/{userId}/followings
//     /api/users/{userId}/followers
    /** 사용자가 팔로우하고 있는 사람들의 목록 **/
    // 팔로잉 목록을 가져오는 API
    @GetMapping("/{userId}/followings")
    public ResponseEntity<?> getFollowingUsers(@PathVariable Long userId) {
        List<SearchUserInfoDto> followingUsers = userService.getFollowingUsers(userId);
        return ResponseEntity.ok(followingUsers);
    }

}
