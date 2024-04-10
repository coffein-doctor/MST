package com.caffeinedoctor.userservice.dto.request.user;

import com.caffeinedoctor.userservice.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginRequestDto {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "username must not be blank")
    private String username;
    private String profileImageUrl;

    @Builder
    public UserLoginRequestDto(String email, String username, String profileImageUrl) {
        this.email = email;
        this.username = username;
        this.profileImageUrl = profileImageUrl;

    }

}