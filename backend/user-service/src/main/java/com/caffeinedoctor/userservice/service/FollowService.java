package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.response.FollowDto;

public interface FollowService {
    // 팔로우 관계 생성
    FollowDto createFollow(Long followingId, Long followerId);
}
