package com.caffeinedoctor.userservice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {

    private String tokenUri;
    private String userInfoUri;
    private String grantType;
    private String clientId;
    private String redirectUri;
}