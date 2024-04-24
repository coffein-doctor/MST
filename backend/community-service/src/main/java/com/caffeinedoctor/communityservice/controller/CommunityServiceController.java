package com.caffeinedoctor.communityservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CommunityServiceController {

    Environment env;

    public CommunityServiceController(Environment env) {
        this.env = env;
    }

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
    public String check(HttpServletRequest request) {
        log.info("Server port = {}", request.getServerPort());

        return String.format("Hi, there. This is a message from Community Service on PORT %s",
                env.getProperty("local.server.port"));
    }
}
