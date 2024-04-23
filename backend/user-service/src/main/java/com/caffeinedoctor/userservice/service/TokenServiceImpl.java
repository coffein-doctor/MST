package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.common.util.CookieUtil;
import com.caffeinedoctor.userservice.dto.response.TokenStatusDto;
import com.caffeinedoctor.userservice.entitiy.Refresh;
import com.caffeinedoctor.userservice.enums.TokenProcessResult;
import com.caffeinedoctor.userservice.repository.RefreshRepository;
import com.caffeinedoctor.userservice.security.jwt.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
//@Transactional(readOnly = true) // (성능 최적화 - 읽기 전용에만 사용)
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class TokenServiceImpl implements TokenService {
    //jwt관리 및 검증 utill
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Value("${JWT.ACCESS-TOKEN.EXPIRE-LENGTH}")
    private long accessTokenExpireLength;
    @Value("${JWT.REFRESH-TOKEN.EXPIRE-LENGTH}")
    private long refreshTokenExpireLength;

    @Value("${JWT.ACCESS-TOKEN.EXPIRE-LENGTH}")
    private int accessCookieExpireLength;
    @Value("${JWT.REFRESH-TOKEN.EXPIRE-LENGTH}")
    private int refreshCookieExpireLength;

    //만료기간이 지난 토큰은 스케줄러를 돌려서 삭제하라.
    /** 토큰 지우기 **/
    @Transactional
    @Override
    public TokenStatusDto removeToken(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = CookieUtil.getRefreshToken(request);

        //토큰 검증 로직
        TokenStatusDto verifyResult = verifyRefreshToken(refresh);
        if (!verifyResult.isSuccessful()) {
            log.info("refresh 토큰 검증 실패");
            return verifyResult; // 토큰 검증에 실패한 경우 해당 응답 반환
        }

        log.info("로그아웃 또는 회원탈퇴 수행: refresh 토큰 삭제");
        //로그아웃 또는 회원 탈퇴 수행
        //Refresh 토큰 DB에서 제거
        refreshRepository.deleteByRefreshToken(refresh);

        //Access 토큰 Cookie 쿠키 삭제
        CookieUtil.expireCookie(response, "access");
        //Refresh 토큰 Cookie 쿠키 삭제
        CookieUtil.expireCookie(response, "refresh");

        return new TokenStatusDto(true, TokenProcessResult.TOKEN_DELETION_SUCCESS, TokenProcessResult.TOKEN_DELETION_SUCCESS.getMessage());
    }

    /** Refresh 토큰으로 Access 토큰을 재발급 **/
    @Transactional
    @Override
    public TokenStatusDto reissueToken(HttpServletRequest request, HttpServletResponse response) {
        log.info("Refresh 토큰으로 Access 토큰을 재발급 ");

        // Get refresh token
        String refresh = CookieUtil.getRefreshToken(request);

        // 토큰 검증 로직
        TokenStatusDto verifyResult = verifyRefreshToken(refresh);
        if (!verifyResult.isSuccessful()) {
            log.info("refresh 토큰 검증 실패");
            return verifyResult; // 토큰 검증에 실패한 경우 해당 응답 반환
        }

        // Extract username and role from refresh token
        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        log.info("새로운 access, refresh 토큰 생성");
        //make new JWT
        //Create new access token
        String newAccess = jwtUtil.createJwt("access", username, role, accessTokenExpireLength);
        //24시간
        String newRefresh = jwtUtil.createJwt("refresh", username, role, refreshTokenExpireLength);

        log.info("새로운 refresh 토큰 갱신");
        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        //삭제
        refreshRepository.deleteByRefreshToken(refresh);
        //저장
        addRefreshEntity(username, newRefresh, refreshTokenExpireLength);

        //response
        //1. Set the new access token in response header
//        response.setHeader("access", newAccess);
        //2. Set the new access token in response Cookie
        response.addCookie(CookieUtil.createAccessCookie("access", newAccess, accessCookieExpireLength));
        //쿠키로 응답
        response.addCookie(CookieUtil.createRefreshCookie("refresh", newRefresh, refreshCookieExpireLength));
        //상태 코드: 200 응답 보내기
        response.setStatus(HttpStatus.OK.value());

        return new TokenStatusDto(true, TokenProcessResult.TOKEN_REISSUE_SUCCESS, TokenProcessResult.TOKEN_REISSUE_SUCCESS.getMessage());
    }

    //refresh토큰 저장
    @Transactional
    @Override
    public void addRefreshEntity(String username, String newRefreshToken, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = Refresh.builder()
                .username(username)
                .refreshToken(newRefreshToken)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refreshEntity);
    }

    //유저의 토큰 모두 삭제
    @Override
    public void deleteExistingRefreshTokens(String username) {
        refreshRepository.deleteByUsername(username);
    }

    //토큰 검증 로직
    private TokenStatusDto verifyRefreshToken(String refresh) {
        log.info("refresh 토큰 검증 시작");

        // 쿠키가 없을 수 있다.
        if (refresh == null) {
            log.info("Refresh 토큰 없음: refresh token null");
            return new TokenStatusDto(false, TokenProcessResult.NO_REFRESH_TOKEN_IN_COOKIE, TokenProcessResult.NO_REFRESH_TOKEN_IN_COOKIE.getMessage());
        }
        // 리프래쉬 토큰이 쿠키에 있다.
        // 만료 채크
        // Check if refresh token is expired
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            log.info("Refresh 토큰 만료: refresh token expired");
            return new TokenStatusDto(false, TokenProcessResult.REFRESH_TOKEN_EXPIRED, TokenProcessResult.REFRESH_TOKEN_EXPIRED.getMessage());
        }

        // 리프레쉬 토큰 만료 안됐으면 리프레쉬 토큰이 맞는 지 확인
        // Check if token category is refresh
        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            log.info("잘못된 Refresh 토큰: invalid refresh token");
            return new TokenStatusDto(false, TokenProcessResult.INCORRECT_TOKEN_TYPE, TokenProcessResult.INCORRECT_TOKEN_TYPE.getMessage());
        }

        log.info("refresh 토큰 DB 일치 여부 체크");
        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefreshToken(refresh);
        if (!isExist) {
            log.info("서버에 없는 잘못된 Refresh 토큰: invalid refresh token");
            return new TokenStatusDto(false, TokenProcessResult.REFRESH_TOKEN_NOT_FOUND, TokenProcessResult.REFRESH_TOKEN_NOT_FOUND.getMessage());
        }
        log.info("Refresh 토큰 검증 성공: Refresh token successfully verified.");
        return new TokenStatusDto(true, null, null);
    }

}
