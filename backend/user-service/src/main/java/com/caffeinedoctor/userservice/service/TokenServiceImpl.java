package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.entitiy.Refresh;
import com.caffeinedoctor.userservice.repository.RefreshRepository;
import com.caffeinedoctor.userservice.security.jwt.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
//@Transactional(readOnly = true) // (성능 최적화 - 읽기 전용에만 사용)
@RequiredArgsConstructor // 파이널 필드만 가지고 생성사 주입 함수 만듬 (따로 작성할 필요 없다.)
@Slf4j
public class TokenServiceImpl implements TokenService {
    //jwt관리 및 검증 utill
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    //만료기간이 지난 토큰은 스케줄러를 돌려서 삭제하라.
    /** 토큰 지우기 **/
    @Override
    public ResponseEntity<String> removeToken(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = getRefreshToken(request);
        ResponseEntity<String> verifyResult = verifyRefreshToken(refresh);
        if (!verifyResult.getStatusCode().is2xxSuccessful()) {
            return verifyResult; // 토큰 검증에 실패한 경우 해당 응답 반환
        }

        log.info("refresh 토큰 검증 완료");

        log.info("로그아웃 진행: refresh 토큰 삭제");
        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshRepository.deleteByRefreshToken(refresh);

        //Refresh 토큰 Cookie 값 0
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        //만료 쿠키로 응답
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** Refresh토큰으로 Access 토큰을 재발급 **/
    @Override
    public ResponseEntity<String> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        log.info("Refresh토큰으로 Access 토큰을 재발급 ");

        // Get refresh token
        String refresh = getRefreshToken(request);
        ResponseEntity<String> verifyResult = verifyRefreshToken(refresh);
        if (!verifyResult.getStatusCode().is2xxSuccessful()) {
            return verifyResult; // 토큰 검증에 실패한 경우 해당 응답 반환
        }

        log.info("refresh 토큰 검증 완료");

        // Extract username and role from refresh token
        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        log.info("새로운 access, refresh 토큰 생성");
        //make new JWT
        //Create new access token
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        //24시간
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        log.info("새로운 refresh 토큰 갱신");
        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByRefreshToken(refresh);
        addRefreshEntity(username, newRefresh, 86400000L);


        //response
        //Set the new access token in response header
        response.setHeader("access", newAccess);
        //쿠키로 응답
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 쿠키에서 가져온다.
    private String getRefreshToken(HttpServletRequest request) {
        log.info("쿠키에서 refresh 토큰 찾기");

        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                    break;
                }
            }
        }
        return refresh;
    }

    //쿠키 생성 함수
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    //refresh토큰 저장
    private void addRefreshEntity(String username, String newRefreshToken, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = Refresh.builder()
                .username(username)
                .refreshToken(newRefreshToken)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refreshEntity);
    }

    //토큰 검증 로직
    public ResponseEntity<String> verifyRefreshToken(String refresh) {
        log.info("refresh 토큰 검증 시작");

        // 쿠키가 없을 수 있다.
        if (refresh == null) {
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }
        // 리프래쉬 토큰이 쿠키에 있다.
        // 만료 채크
        // Check if refresh token is expired
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 리프레쉬 토큰 만료 안됐으면 리프레쉬 토큰이 맞는 지 확인
        // Check if token category is refresh
        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        log.info("refresh 토큰 DB 일치 여부 체크");
        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefreshToken(refresh);
        if (!isExist) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
