package com.caffeinedoctor.userservice.entitiy;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String gender; // [m, w]

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private int weight;

    private String profile;

    private String introduction;

    @Column(name = "created_date")
    private LocalDateTime SignUpDate;

}
