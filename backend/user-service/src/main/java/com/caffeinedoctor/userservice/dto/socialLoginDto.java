package com.caffeinedoctor.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class socialLoginDto {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "username must not be blank")
    private String username;
    private String profileImageUrl;

    @Builder
    public socialLoginDto(String email, String username, String profileImageUrl) {
        this.email = email;
        this.username = username;
        this.profileImageUrl = profileImageUrl;

    }

}