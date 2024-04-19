package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "JWT", description = "JWT API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    /** Refresh로 Access 토큰 재발급 **/
    @Operation(
            summary = "Access 토큰 재발급",
            description = "Refresh토큰으로 Access 토큰을 재발급합니다."
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
    @PostMapping("/reissue")
    public ResponseEntity<String> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        return tokenService.reissueToken(request, response);
    }



    /** 로그아웃으로 refresh, access 토큰 삭제 **/
    @Operation(
            summary = "로그아웃 토큰 삭제",
            description = "로그아웃으로 refresh 토큰과 access 토큰을 삭제합니다."
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
        return tokenService.removeToken(request, response);
    }
}