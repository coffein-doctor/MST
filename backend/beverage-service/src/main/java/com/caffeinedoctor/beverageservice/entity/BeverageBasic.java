package com.caffeinedoctor.beverageservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeverageBasic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder.Default
    @OneToMany(mappedBy = "basic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Beverage> beverages = new ArrayList<>();
}
