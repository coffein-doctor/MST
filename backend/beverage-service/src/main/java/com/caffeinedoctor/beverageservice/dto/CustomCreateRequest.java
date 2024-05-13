package com.caffeinedoctor.beverageservice.dto;

import lombok.Data;

@Data
public class CustomCreateRequest {
    private String company;
    private String name;
    private String size;
    private Long caffeine;
    private Long sugar;
    private Long volume;
    private Long rating;
    private String content;
}
