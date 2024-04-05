package com.caffeinedoctor.userservice.entitiy;



import com.caffeinedoctor.userservice.entitiy.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private String uuid;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
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
    public User(String email, String nickname, LocalDate birth, Gender gender, int height, int weight, String profileImageUrl, String introduction) {
        this.uuid =UUID.randomUUID().toString(); // UUID 생성 및 할당
//        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
        this.signUpDate = LocalDateTime.now();
    }

    // 로그인 시간 업데이트 함수
    public void updateLoginDate() {
        this.loginDate = LocalDateTime.now();
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

    public void updateProfile(String profile) {
        this.profileImageUrl = profile;
    }

    public void updateIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
