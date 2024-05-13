package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.FollowResponseDto;

public interface FollowService {
    // 팔로우 관계 생성
    FollowResponseDto createFollow(Long followingId, Long followerId);
}
