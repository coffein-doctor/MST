package com.caffeinedoctor.userservice.entitiy;



import com.caffeinedoctor.userservice.entitiy.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
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

    private String profile;

    private String introduction;

    @Column(name = "created_date")
    private LocalDateTime signUpDate;

    @Column(name = "login_date")
    private LocalDateTime loginDate;

    // 생성자
    @Builder
    public User(String email, String nickname, LocalDate birth, Gender gender, int height, int weight, String profile, String introduction, LocalDateTime signUpDate, LocalDateTime loginDate) {
        this.uuid = UUID.randomUUID().toString(); // UUID 생성 및 할당
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.profile = profile;
        this.introduction = introduction;
        this.signUpDate = LocalDateTime.now();
    }

    // 로그인 시간 업데이트 함수
    public void updateLoginDate() {
        this.loginDate = LocalDateTime.now();
    }

}
