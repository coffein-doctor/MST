package com.caffeinedoctor.beverageservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Baverage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    private LocalDateTime drinkDate;

    @Column(nullable = false)
    private Boolean isCustom;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "custom_id")
//    private BavaerageCustom custom;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "baverage_id")
//    private BaverageBasic basic;
}
