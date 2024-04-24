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
public class BeverageCustom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String company;

    @Column(length = 100, nullable = false)
    private String size;

    @Builder.Default
    @Column(length = 10, nullable = false)
    private Long volume = 0L;

    @Builder.Default
    @Column(length = 10, nullable = false)
    private Long caffeine = 0L;

    @Builder.Default
    @Column(length = 10, nullable = false)
    private Long sugar = 0L;

    @Builder.Default
    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Beverage> beverages = new ArrayList<>();
}
