package com.caffeinedoctor.beverageservice.dto;

import com.caffeinedoctor.beverageservice.entity.BeverageCustom;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BeverageCustomInfo {
    private Long id;
    private String company;
    private String name;
    private String size;
    private Long caffeine;
    private Long sugar;
    private Long volume;

    public BeverageCustomInfo(BeverageCustom custom) {
        this.id = custom.getId();
        this.company = custom.getCompany();
        this.name = custom.getName();
        this.size = custom.getSize();
        this.caffeine = custom.getCaffeine();
        this.sugar = custom.getSugar();
        this.volume = custom.getVolume();
    }
}
