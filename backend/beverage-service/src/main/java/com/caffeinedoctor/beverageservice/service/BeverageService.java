package com.caffeinedoctor.beverageservice.service;

import com.caffeinedoctor.beverageservice.dto.BasicInfoRequest;
import com.caffeinedoctor.beverageservice.dto.BeverageBasicInfo;
import com.caffeinedoctor.beverageservice.entity.BeverageBasic;
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

    public BeverageBasicInfo basicInfo(BasicInfoRequest dto) {
        BeverageBasic beverageBasic = basicRepository.findByName(dto.getName()).orElseThrow(
                () -> new RuntimeException("등록되지 않은 제품입니다"));

        return new BeverageBasicInfo(beverageBasic);
    }
}
