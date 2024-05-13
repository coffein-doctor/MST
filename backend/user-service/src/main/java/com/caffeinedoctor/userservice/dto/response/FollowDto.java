package com.caffeinedoctor.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class FollowDto {
    private Long followId;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;

    @Builder
    public FollowDto(Long followId, Long followerId, Long followingId) {
        this.followId = followId;
        this.followerId = followerId;
        this.followingId = followingId;
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
