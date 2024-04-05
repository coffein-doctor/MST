package com.caffeinedoctor.userservice.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLoginRequestDto {
//    private long kakaoId;
//    @Enumerated(EnumType.STRING)
//    private UserType userType;
//    private String connectedAt;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
    private String password;
//    private String profileImageUrl;

}