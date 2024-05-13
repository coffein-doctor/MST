package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.response.FollowDto;
import com.caffeinedoctor.userservice.service.FollowServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Follow", description = "Follow 관리 API")
@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
@Slf4j
public class FollowController {

    private final FollowServiceImpl followService;

    /** 팔로우 관계 생성 **/
    @Operation(
            summary = "사용자 팔로우",
            description = "특정 사용자를 팔로우합니다. 팔로워 ID와 팔로잉 ID를 입력해주세요. followerId는 팔로우하는 사용자의 ID이고, followingId는 팔로우되는 사용자의 ID입니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "팔로우에 성공하였습니다.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FollowDto.class) // FollowResponseDto가 API 응답에 사용됨
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청입니다. 입력 값을 확인해주세요.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
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
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "이미 팔로우 관계가 존재합니다.",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/{followerId}/{followingId}")
    public ResponseEntity<?> follow(
            @PathVariable Long followingId,
            @PathVariable Long followerId
    ) {
        try {
            // followerId는 팔로우하는 사용자의 ID이고, followingId는 팔로우되는 사용자의 ID
            FollowDto followDto = followService.createFollow(followerId, followingId);
            return ResponseEntity.ok(followDto);
        } catch (EntityNotFoundException ex) {
            // EntityNotFoundException의 경우, 존재하지 않는 엔티티 참조를 알려주는 404 상태 코드 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalStateException ex) {
            // 팔로우 관계가 이미 존재하는 경우, 409 상태 코드 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
