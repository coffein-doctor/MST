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
    private String username;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    private String email;
    private String profileImageUrl;

    @Builder
    private KakaoLoginResponseDto(String username, UserStatus userStatus, String email, String profileImageUrl) {
        this.username = username;
        this.userStatus = userStatus;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

}
