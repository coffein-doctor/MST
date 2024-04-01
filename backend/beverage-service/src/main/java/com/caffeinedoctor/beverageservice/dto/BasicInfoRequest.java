package com.caffeinedoctor.beverageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicInfoRequest {
    private String company;
    private String name;
}
