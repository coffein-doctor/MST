package com.caffeinedoctor.userservice.dto.request.user;

import com.caffeinedoctor.userservice.entitiy.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class SignUpUserRequestDto {

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Nickname must not be blank")
    private String nickname;

    @NotNull(message = "Birth must not be null")
    private LocalDate birth;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotNull(message = "Height must not be null")
    @Min(value = 1, message = "Height must be greater than 0")
    private Integer height;

    @NotNull(message = "Weight must not be null")
    @Min(value = 1, message = "Weight must be greater than 0")
    private Integer weight;

    private MultipartFile profileImg;

    private String introduction;

    private LocalDateTime createAt;

//    private LocalDateTime signUpDate;
//
//    private LocalDateTime loginDate;

}
