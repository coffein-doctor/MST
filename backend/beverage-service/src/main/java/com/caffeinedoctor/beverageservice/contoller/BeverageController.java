package com.caffeinedoctor.beverageservice.contoller;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeverageController {
    @GetMapping("/")
    public String getServiceName(){
        return  "beverage-service";
    }
}
