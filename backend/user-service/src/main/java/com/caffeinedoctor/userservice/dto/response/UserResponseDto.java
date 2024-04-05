package com.caffeinedoctor.userservice.dto.response;

import com.caffeinedoctor.userservice.dto.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private long kakaoId;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String connectedAt;
    private String email;
    private String profileImageUrl;

    @Builder
    private UserResponseDto(long kakaoId, UserType userType, String email, String connectedAt, String profileImageUrl) {
        this.kakaoId = kakaoId;
        this.userType = userType;
        this.connectedAt = connectedAt;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

}
