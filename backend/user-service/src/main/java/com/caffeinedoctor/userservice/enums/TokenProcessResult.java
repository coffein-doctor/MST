package com.caffeinedoctor.userservice.enums;

public enum TokenProcessResult {
    // 성공 이유
    SUCCESS("토큰 처리가 성공적으로 완료되었습니다."),
    TOKEN_DELETION_SUCCESS("토큰 삭제가 성공적으로 완료되었습니다."),
    TOKEN_REISSUE_SUCCESS("토큰 재발행이 성공적으로 완료되었습니다."),

    // 실패 이유
    // 쿠키 내 Refresh 토큰 미존재
    NO_REFRESH_TOKEN_IN_COOKIE("Refresh 토큰이 존재하지 않습니다."),
    // Refresh 토큰 만료
    REFRESH_TOKEN_EXPIRED("Refresh 토큰이 만료되었습니다."),
    // 잘못된 토큰 유형 제공
    INCORRECT_TOKEN_TYPE("잘못된 유형의 토큰이 전송되었습니다."),
    // 서버에 Refresh 토큰 미존재
    REFRESH_TOKEN_NOT_FOUND("제공된 Refresh 토큰이 존재하지 않습니다."),

    ACCESS_TOKEN_EXPIRED("Access 토큰이 만료되었습니다.");

    private final String message;

    TokenProcessResult(String message) {
        this.message = message;
    }

    public String  getMessage() {
        return message;
    }
}
