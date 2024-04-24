package com.caffeinedoctor.userservice.dto.response;

import com.caffeinedoctor.userservice.enums.TokenProcessResult;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenStatusDto {
    private boolean successful;
    @Enumerated(EnumType.STRING)
    private TokenProcessResult reason;
    private String message;

    @Builder
    public TokenStatusDto(boolean successful, TokenProcessResult reason, String message) {
        this.successful = successful;
        this.reason = reason;
        this.message = message;
    }

}
