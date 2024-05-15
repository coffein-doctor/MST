package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.dto.response.TokenStatusDto;
import com.caffeinedoctor.userservice.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "JWT", description = "JWT Access, Refresh 토큰 관리 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    /** Refresh로 Access 토큰 재발급 **/
    @Operation(
            summary = "Access 토큰 재발급",
            description = "Access 토큰이 만료되었을 때, Refresh 토큰을 사용하여 새로운 Access 토큰을 Header로 발급받습니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully reissued token",
                    headers = {
                    @Header(name = "access", description = "Newly issued access token", schema = @Schema(type = "string"))
            }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Possible reasons: refresh token null, refresh token expired, invalid refresh token"
            ),
    })
    @PostMapping("/reissue")
    public ResponseEntity<String> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        // 엑세스 토큰으로 현재 Redis 정보 삭제
        // TokenService의 reissueToken 메서드 호출하여 결과 받기
        TokenStatusDto tokenStatusDto = tokenService.reissueToken(request, response);
        if (!tokenStatusDto.isSuccessful()) {
            // 재발행 실패 시, HTTP 상태 코드 400(Bad Request)와 함께 결과 반환
            return ResponseEntity.badRequest().body(tokenStatusDto.getMessage());
        }

        // 재발행 성공 시, HTTP 상태 코드 200(OK)와 함께 결과 반환
        return ResponseEntity.ok(tokenStatusDto.getMessage());
    }



    /** 로그아웃으로 refresh, access 토큰 삭제 **/
    @Operation(
            summary = "로그아웃 및 토큰 만료",
            description = "사용자가 로그아웃할 때, 쿠키의 Refresh 토큰을 만료시켜 삭제합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully reissued token"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Possible reasons: refresh token null, refresh token expired, invalid refresh token"
            ),
    })
    @DeleteMapping("/logout")
    public ResponseEntity<String> removeToken(HttpServletRequest request, HttpServletResponse response) {
        // 엑세스 토큰으로 현재 Redis 정보 삭제
        TokenStatusDto tokenStatusDto = tokenService.removeToken(request, response);
        if (!tokenStatusDto.isSuccessful()) {
            // 토큰 삭제 실패 응답 처리
            return ResponseEntity.badRequest().body(tokenStatusDto.getMessage());
        }

        // 토큰 삭제 성공 응답 처리
        return ResponseEntity.ok(tokenStatusDto.getMessage());
    }
}