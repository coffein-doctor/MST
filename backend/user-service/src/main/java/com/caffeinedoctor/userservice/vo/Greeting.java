package com.caffeinedoctor.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 용도가 정해지지 않았으면 @Component로 등록
@Component
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Greeting {

    @Value("${greeting.message}")
    private String message;
}
