package com.caffeinedoctor.beverageservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private Beverage beverage;

    @Column(nullable = false)
    private Long rating;

    @Column(nullable = false)
    private String content;

    public void setBeverage(Beverage beverage) {
        if (this.beverage != null) {
            this.beverage.getFeeds().remove(this);
        }

        this.beverage = beverage;
        this.beverage.getFeeds().add(this);
    }
}
