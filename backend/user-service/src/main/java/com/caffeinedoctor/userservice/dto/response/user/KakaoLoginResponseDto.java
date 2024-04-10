package com.caffeinedoctor.userservice.dto.response.user;

import com.caffeinedoctor.userservice.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginResponseDto {
    private long kakaoId;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    private String connectedAt;
    private String email;
    private String profileImageUrl;

    @Builder
    private KakaoLoginResponseDto(long kakaoId, UserStatus userStatus, String email, String connectedAt, String profileImageUrl) {
        this.kakaoId = kakaoId;
        this.userStatus = userStatus;
        this.connectedAt = connectedAt;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

}
