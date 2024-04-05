package com.caffeinedoctor.userservice.dto.request.user;

import com.caffeinedoctor.userservice.entitiy.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@NoArgsConstructor
public class UserRequestDto {

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

    private String profileImgUrl;

    private String introduction;

    @Builder
    public UserRequestDto(String email, String nickname, LocalDate birth, Gender gender, int height, int weight, String profileImgUrl, String introduction) {
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.profileImgUrl = profileImgUrl;
        this.introduction = introduction;
    }

}
