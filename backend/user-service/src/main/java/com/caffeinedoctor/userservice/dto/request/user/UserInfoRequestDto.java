package com.caffeinedoctor.userservice.dto.request.user;

import com.caffeinedoctor.userservice.enums.Gender;
import com.caffeinedoctor.userservice.enums.ActivityLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
public class UserInfoRequestDto {

    @NotBlank(message = "Nickname must not be blank")
    private String nickname;

    @NotNull(message = "Birth must not be null")
    private LocalDate birth;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotNull(message = "activityLevel must not be null")
    private ActivityLevel  activityLevel;

    @NotNull(message = "Height must not be null")
    @Min(value = 1, message = "Height must be greater than 0")
    private double height;

    @NotNull(message = "Weight must not be null")
    @Min(value = 1, message = "Weight must be greater than 0")
    private double weight;

    private String introduction;

    @Builder
    public UserInfoRequestDto(String nickname, LocalDate birth, Gender gender, ActivityLevel  activityLevel, double height, double weight, String introduction) {
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.height = height;
        this.weight = weight;
        this.introduction = introduction;
    }

}
