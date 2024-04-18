package com.caffeinedoctor.userservice.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface ReissueService {

    ResponseEntity<?> reissueToken(HttpServletRequest request, HttpServletResponse response);
}
