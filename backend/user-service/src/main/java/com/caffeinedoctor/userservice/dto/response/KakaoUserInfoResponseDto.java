package com.caffeinedoctor.userservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoResponseDto {
    private Long id;
    private String connected_at;
    private KakaoAccountDto kakao_account;
}
