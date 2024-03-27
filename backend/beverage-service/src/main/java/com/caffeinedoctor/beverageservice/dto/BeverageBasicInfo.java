package com.caffeinedoctor.beverageservice.dto;

import com.caffeinedoctor.beverageservice.entity.BeverageBasic;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BeverageBasicInfo {
    private Long id;
    private String company;
    private String name;
    private Long caffeine;
    private Long sugar;
    private Long volume;

    public BeverageBasicInfo(BeverageBasic basic) {
        this.id = basic.getId();
        this.company = basic.getCompany();
        this.name = basic.getName();
        this.caffeine = basic.getCaffeine();
        this.sugar = basic.getSugar();
        this.volume = basic.getVolume();
    }
}
