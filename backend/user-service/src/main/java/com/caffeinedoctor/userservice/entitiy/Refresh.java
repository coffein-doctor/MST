package com.caffeinedoctor.userservice.entitiy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "refresh")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "refresh_token")
    private String refreshToken;

    private LocalDateTime expiration;

    // 생성자
    @Builder
    public Refresh(String username, String refreshToken, LocalDateTime  expiration){
        this.username = username;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}