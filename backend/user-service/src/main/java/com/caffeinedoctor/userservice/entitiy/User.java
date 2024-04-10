package com.caffeinedoctor.userservice.entitiy;



import com.caffeinedoctor.userservice.enums.Gender;
import com.caffeinedoctor.userservice.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String nickname;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int height;

    private int weight;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    private String introduction;

    @Column(name = "created_date")
    private LocalDateTime signUpDate;

    @Column(name = "login_date")
    private LocalDateTime loginDate;

    // 생성자
    @Builder
    public User(String username, String email, String profileImageUrl) {
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.status  = UserStatus.NEW_USER;
        this.signUpDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    // 로그인 시간 업데이트 함수
    public void updateLoginDate() {
        this.loginDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    // 신규 유저이면 회원 정보를 등록하면서 기존유저로 변경
    public void updateUserStatus() {
        if (this.status == UserStatus.NEW_USER) {
            this.status = UserStatus.EXISTING_USER;
        }
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void updateGender(Gender gender) {
        this.gender = gender;
    }

    public void updateHeight(int height) {
        this.height = height;
    }

    public void updateWeight(int weight) {
        this.weight = weight;
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updateIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
