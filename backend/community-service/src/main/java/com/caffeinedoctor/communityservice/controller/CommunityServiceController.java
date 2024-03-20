package com.caffeinedoctor.communityservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CommunityServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Community service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("community-request") String header) {
        log.info(header);
        return "Hello World in Community Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from Community Service";
    }
}
