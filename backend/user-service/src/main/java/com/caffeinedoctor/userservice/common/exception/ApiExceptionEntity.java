package com.caffeinedoctor.userservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiExceptionEntity {
    private int errorCode;
    private String message;
}