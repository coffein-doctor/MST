package com.caffeinedoctor.userservice.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.Base64;

@Slf4j
public class CookieUtil {

    // 쿠키에서 가져온다.
    public static String getRefreshToken(HttpServletRequest request) {
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
        log.info("쿠키의 refresh 토큰: {}", refresh);
        return refresh;
    }

    //refresh 토큰 쿠키 생성 함수
    //쿠키 만들기
    public static Cookie createRefreshCookie(String key, String value, int expireLength) {
        log.info(key + " 쿠키 생성");
        //value: jwt
        Cookie cookie = new Cookie(key, value);
        //쿠키가 살아있을 시간 (24*60*60 = 24시간)
        cookie.setMaxAge(expireLength);
        //자바스크립트가 해당 쿠키를 가져가지 못하게 설정
        cookie.setHttpOnly(true);
        //쿠키 적용 범위 (user-service)
        cookie.setPath("/user-service/");
        //https 통신에서만 사용 가능
        //cookie.setSecure(true);

        return cookie;
    }

    public static Cookie createAccessCookie(String key, String value, int expireLength) {
        log.info(key + " 쿠키 생성");
        //value: jwt
        Cookie cookie = new Cookie(key, value);
        //쿠키가 살아있을 시간 (60*60 = 1시간)
        cookie.setMaxAge(expireLength);
        //쿠키 적용 범위 (전역)
        cookie.setPath("/");
        //https 통신에서만 사용 가능
        //cookie.setSecure(true);
        //자바스크립트가 해당 쿠키를 가져가지 못하게 설정
        //cookie.setHttpOnly(true);

        return cookie;
    }

    // 쿠키의 이름을 입력받아 쿠키 삭제
    public static void expireCookie(HttpServletResponse response, String cookieName) {
        log.info(cookieName + " 쿠키 삭제 (만료)");
        // 실제로 삭제하는 방법은 없으므로 쿠키 value를 빈 값으로 바꾸고
        // 만료 시간을 0으로 설정해 쿠키가 재생성 되자마자 만료 처리한다.
        // 브라우저는 이전 쿠키를 무시하고 새로운 쿠키를 사용한다.

        //Cookie cookie = new Cookie("refresh", null);
        Cookie cookie = new Cookie(cookieName, null);
        //Refresh 토큰 Cookie 만료시간 0으로 설정하여 생성하자마자 만료시킨다.
        cookie.setMaxAge(0);
        cookie.setPath("/");
        //만료 쿠키로 응답
        response.addCookie(cookie);
    }


    // 객체를 직렬화해 쿠키의 값으로 변환
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize((Serializable) obj));
    }

    // 쿠키를 역직렬화해서 객체로 변환
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }
}