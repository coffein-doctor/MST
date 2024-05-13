package com.caffeinedoctor.beverageservice.service;

import com.caffeinedoctor.beverageservice.dto.*;
import com.caffeinedoctor.beverageservice.entity.Beverage;
import com.caffeinedoctor.beverageservice.entity.BeverageBasic;
import com.caffeinedoctor.beverageservice.entity.BeverageCustom;
import com.caffeinedoctor.beverageservice.entity.Feed;
import com.caffeinedoctor.beverageservice.exception.ResourceNotFoundException;
import com.caffeinedoctor.beverageservice.repository.BeverageBasicRepository;
import com.caffeinedoctor.beverageservice.repository.BeverageCustomRepository;
import com.caffeinedoctor.beverageservice.repository.BeverageRepository;
import com.caffeinedoctor.beverageservice.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeverageService {
    private final BeverageRepository beverageRepository;
    private final BeverageBasicRepository basicRepository;
    private final BeverageCustomRepository customRepository;
    private final FeedRepository feedRepository;

    public List<BeverageBasicInfo> basicInfo(BasicInfoRequest dto) {
        List<BeverageBasic> findByName = basicRepository.findByNameContaining(dto.getWord());
        List<BeverageBasic> findByCompany = basicRepository.findByCompanyContaining(dto.getWord());

        Set<BeverageBasic> beverageSet = new LinkedHashSet<>(findByName);
        beverageSet.addAll(findByCompany);

        List<BeverageBasic> beverages = new ArrayList<>(beverageSet);
        List<BeverageBasicInfo> beverageBasic = new ArrayList<>();

        for (BeverageBasic bb : beverages) {
            beverageBasic.add(new BeverageBasicInfo(bb));
        }

        return beverageBasic;
    }

    @Transactional
    public BeverageCustomInfo customCreate(CustomCreateRequest dto) {
//        User requester = userRepository.findById(requesterId)
//                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 회원입니다."));

        BeverageCustom beverageCustom = BeverageCustom.builder()
                .company(dto.getCompany())
                .name(dto.getName())
                .size(dto.getSize())
                .caffeine(dto.getCaffeine())
                .sugar(dto.getSugar())
                .volume(dto.getVolume())
                .build();
        customRepository.save(beverageCustom);

        Beverage beverage = Beverage.builder()
//                .user(requester)
                .isCustom(true)
                .custom(beverageCustom)
                .build();
        beverageRepository.save(beverage);

        Feed feed = Feed.builder()
                .beverage(beverage)
                .rating(dto.getRating())
                .content(dto.getContent())
                .build();
        feedRepository.save(feed);

        return new BeverageCustomInfo(beverageCustom);
    }

    @Transactional
    public void basicCreate(BasicCreateRequest dto) {
//        User requester = userRepository.findById(requesterId)
//                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 회원입니다."));

        BeverageBasic requestBasic = beverageRepository.findById(dto.getBeverageId())
                .orElseThrow(() -> new ResourceNotFoundException("등록되지 않은 음료입니다.")).getBasic();

        BeverageBasic beverageBasic = basicRepository.findById(requestBasic.getId())
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 음료입니다."));

        Beverage beverage = Beverage.builder()
//                .user(requester)
                .isCustom(false)
                .basic(beverageBasic)
                .build();
        beverageRepository.save(beverage);

        Feed feed = Feed.builder()
                .beverage(beverage)
                .rating(dto.getRating())
                .content(dto.getContent())
                .build();
        feedRepository.save(feed);
    }
}
