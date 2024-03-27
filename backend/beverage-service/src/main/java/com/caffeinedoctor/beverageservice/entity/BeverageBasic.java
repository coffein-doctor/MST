package com.caffeinedoctor.beverageservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeverageBasic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String company;

    @Column(length = 100, nullable = false)
    private String size;

    @Column(length = 10)
    private Long volume;

    @Column(length = 10)
    private Long caffeine;

    @Column(length = 10)
    private Long sugar;
}
