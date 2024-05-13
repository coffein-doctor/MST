package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.entitiy.Follow;

public interface FollowService {
    // 팔로우 관계 생성
    Follow createFollow(Long followingId, Long followerId);
}
