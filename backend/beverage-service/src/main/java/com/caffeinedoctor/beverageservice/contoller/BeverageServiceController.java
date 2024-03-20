package com.caffeinedoctor.beverageservice.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BeverageServiceController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Beverage service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("beverage-request") String header) {
        log.info(header);
        return "Hello World in Beverage Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from Beverage Service";
    }

}
