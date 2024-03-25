package com.caffeinedoctor.beverageservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "profile")
//    private DateFile profile;

    @CreatedDate
    private LocalDateTime createdDate;

    @Column(length = 10, nullable = false)
    private Long height;

    @Column(length = 10, nullable = false)
    private Long weight;

    @Column(length = 10, nullable = false)
    private String gender;

    @Column(length = 20, nullable = false)
    private String birth;

    @Column(length = 200, nullable = false)
    private String introduction;
}
