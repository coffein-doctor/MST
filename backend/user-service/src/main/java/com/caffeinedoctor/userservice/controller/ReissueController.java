package com.caffeinedoctor.userservice.controller;

import com.caffeinedoctor.userservice.security.jwt.JWTUtil;
import com.caffeinedoctor.userservice.service.ReissueService;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "JWT", description = "JWT 관리 API")
@RestController
@RequestMapping("/reissue")
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class ReissueController {

    private final ReissueService reissueService;

    /** Refresh로 Access 토큰 재발급 **/
    @Operation(
            summary = "Access 토큰 재발급",
            description = "Refresh토큰으로 Access 토큰을 재발급합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully reissued token",
                    content = @Content(
                            schema = @Schema(implementation = Long.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Possible reasons: refresh token null, refresh token expired, invalid refresh token",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PostMapping
    public ResponseEntity<?> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        return reissueService.reissueToken(request, response);
    }
}