package com.caffeinedoctor.userservice.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> apiExceptionHandler(final ApiException e) {

        return new ResponseEntity<>(
                new ApiExceptionEntity(
                        e.getError().getErrorCode(),
                        e.getError().getMessage()
                ),
                e.getError().getHttpStatus()
        );
    }
}