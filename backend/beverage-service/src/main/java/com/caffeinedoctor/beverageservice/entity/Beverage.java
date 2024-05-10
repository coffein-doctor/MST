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
public class Beverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @CreatedDate
    private LocalDateTime drinkDate;

    @Column(nullable = false)
    private Boolean isCustom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_id")
    private BeverageCustom custom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beverage_id")
    private BeverageBasic basic;

    @OneToMany(mappedBy = "beverage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feed> feeds = new ArrayList<>();

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getBeverages().remove(this);
        }

        this.user = user;
        this.user.getBeverages().add(this);
    }

    public void setCustom(BeverageCustom custom) {
        if (this.custom != null) {
            this.custom.getBeverages().remove(this);
        }

        this.custom = custom;
        this.custom.getBeverages().add(this);
    }

    public void setBasic(BeverageBasic basic) {
        if (this.basic != null) {
            this.basic.getBeverages().remove(this);
        }

        this.basic = basic;
        this.basic.getBeverages().add(this);
    }
}
