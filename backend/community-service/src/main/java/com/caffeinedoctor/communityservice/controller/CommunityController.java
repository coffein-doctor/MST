package com.caffeinedoctor.communityservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController {

    @GetMapping("/")
    public String getServiceName(){
        return "community-service";
    }
}
