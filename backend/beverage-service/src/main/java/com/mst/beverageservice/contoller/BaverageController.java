package com.mst.beverageservice.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaverageController {
    @GetMapping("/")
    public String getServiceName(){
        return  "baverage-service";
    }
}
