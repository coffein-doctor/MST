package com.caffeinedoctor.userservice.dto.response.oauth2;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2UserResponseDto {

    private String role;
    @NotBlank(message = "username(loginID) must not be blank")
    private String username;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "profileImageUrl must not be blank")
    private String profileImageUrl;

    @Builder
    private OAuth2UserResponseDto(String role, String username, String email, String profileImageUrl) {
        this.role = role;
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

}
