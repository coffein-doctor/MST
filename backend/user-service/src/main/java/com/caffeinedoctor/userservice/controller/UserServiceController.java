package com.caffeinedoctor.userservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserServiceController {
    // application.yml 의 환경 설정 정보 가져오기
    // 변수 주입을 위해 @Autowired 대신 생성자 주입
    Environment env;

    // 변수를 주입 받기위해 @Autowired 사용이 일반적이었는데 인텔리제이는 생성자 주입을 권장
    public UserServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the User service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("user-request") String header) {
        log.info(header);
        return "Hello World in User Service";
    }

    // 현재 사용되는 랜덤 포트 번호 출력
    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        // 1. MVC에서 HttpServletRequest 선언으로 port 번호 가져오는 방법
        log.info("Server port = {}", request.getServerPort());

        // 2. Environment로 port번호 가져오는 방법
        return String.format("Hi, there. This is a message from User Service on PORT %s",
                env.getProperty("local.server.port"));
    }
}
