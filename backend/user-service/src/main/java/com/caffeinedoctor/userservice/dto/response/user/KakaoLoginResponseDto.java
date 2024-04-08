package com.caffeinedoctor.userservice.dto.response.user;

import com.caffeinedoctor.userservice.enums.UserType;
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
    private UserType userType;
    private String connectedAt;
    private String email;
    private String profileImageUrl;

    @Builder
    private KakaoLoginResponseDto(long kakaoId, UserType userType, String email, String connectedAt, String profileImageUrl) {
        this.kakaoId = kakaoId;
        this.userType = userType;
        this.connectedAt = connectedAt;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

}
