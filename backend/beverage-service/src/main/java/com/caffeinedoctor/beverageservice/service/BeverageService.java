package com.caffeinedoctor.beverageservice.service;

import com.caffeinedoctor.beverageservice.repository.BeverageBasicRepository;
import com.caffeinedoctor.beverageservice.repository.BeverageCustomRepository;
import com.caffeinedoctor.beverageservice.repository.BeverageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeverageService {
    private final BeverageRepository beverageRepository;
    private final BeverageBasicRepository basicRepository;
    private final BeverageCustomRepository customRepository;
}
