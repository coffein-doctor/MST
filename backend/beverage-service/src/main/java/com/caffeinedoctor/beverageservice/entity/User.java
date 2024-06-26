package com.caffeinedoctor.beverageservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column
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

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Beverage> beverages = new ArrayList<>();
}
