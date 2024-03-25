package com.caffeinedoctor.userservice.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class SignUpUserRequestDto {
    @NotBlank(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 8, message = "Password must not be equal or grater than 8 characters")
    private String password;

    @NotBlank(message = "Nickname cannot be null")
    @Size(min = 2, message = "Nickname not be less than two characters")
    private String nickname;

    private String birth;

    private String gender;

    private Long height;

    private Long weight;

    private MultipartFile profileImg;

    private String introduction;

    private LocalDateTime createAt;

    private String encryptedPwd;
}
