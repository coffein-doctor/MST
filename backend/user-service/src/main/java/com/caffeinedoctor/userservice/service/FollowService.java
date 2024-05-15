package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.request.follow.FollowRequestDto;
import com.caffeinedoctor.userservice.dto.response.FollowResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface FollowService {
    // 팔로우 관계 생성
    @Transactional
    FollowResponseDto createFollow(FollowRequestDto requestDto);
}
