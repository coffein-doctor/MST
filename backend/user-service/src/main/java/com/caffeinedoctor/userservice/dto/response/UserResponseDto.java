package com.caffeinedoctor.userservice.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private long kakaoId;
    private String connectedAt;
    private String email;
    private String profileImageUrl;

    @Builder
    private UserResponseDto(long kakaoId, String email, String connectedAt, String profileImageUrl) {
        this.kakaoId = kakaoId;
        this.connectedAt = connectedAt;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

}
