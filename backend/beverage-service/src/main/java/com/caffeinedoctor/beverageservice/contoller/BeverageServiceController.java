package com.caffeinedoctor.beverageservice.contoller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BeverageServiceController {
    Environment env;

    public BeverageServiceController(Environment env) {
        this.env = env;
    }

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

    // 현재 사용되는 랜덤 포트 번호 출력
    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port = {}", request.getServerPort());

        return String.format("Hi, there. This is a message from Beverage Service on PORT %s",
                env.getProperty("local.server.port"));
    }

}
