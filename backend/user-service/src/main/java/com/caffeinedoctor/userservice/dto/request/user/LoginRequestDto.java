package com.caffeinedoctor.userservice.dto.request.user;

import com.caffeinedoctor.userservice.dto.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginRequestDto {
    private long kakaoId;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String connectedAt;
    private String email;
    private String profileImageUrl;

}