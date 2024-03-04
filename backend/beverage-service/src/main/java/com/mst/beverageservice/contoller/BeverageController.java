package com.mst.beverageservice.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeverageController {
    @GetMapping("/")
    public String getServiceName(){
        return  "beverage-service";
    }
}
