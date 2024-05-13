package com.caffeinedoctor.beverageservice.dto;

import com.caffeinedoctor.beverageservice.entity.Beverage;
import lombok.Data;

@Data
public class BasicCreateRequest {
    private Long beverageId;
    private Long rating;
    private String content;
}
