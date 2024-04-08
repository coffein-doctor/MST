package com.caffeinedoctor.userservice.dto.request.user;

import com.caffeinedoctor.userservice.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginRequestDto {
    private long kakaoId;
    @NotBlank(message = "userType must not be blank. 'NEW_MEMBER' or 'EXISTING_MEMBER'")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String connectedAt;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "profileImageUrl must not be blank")
    private String profileImageUrl;

}