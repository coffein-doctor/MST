package com.caffeinedoctor.userservice.dto.response.user;

import com.caffeinedoctor.userservice.enums.ActivityLevel;
import com.caffeinedoctor.userservice.enums.Gender;
import com.caffeinedoctor.userservice.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserDetailsDto {
    public Long userId;
    public String username;
    public String email;
    public UserStatus status;
    public String nickname;
    public LocalDate birth;
    public Gender gender;
    public int height;
    public int weight;
    public ActivityLevel activityLevel;
    public String profileImageUrl;
    public String introduction;

    // 생성자, 게터, 세터 등 필요한 메서드 추가
    @Builder
    public UserDetailsDto(Long userId, String username, String email, UserStatus status, String nickname,
                          LocalDate birth, Gender gender, int height, int weight, ActivityLevel activityLevel,
                          String profileImageUrl, String introduction) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.status = status;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
    }

}
