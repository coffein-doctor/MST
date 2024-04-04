package com.caffeinedoctor.userservice.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserResponseDto {
    private String userId;
    private String email;
    private String nickname;
}
