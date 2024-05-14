package com.caffeinedoctor.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class FollowResponseDto {
    private Long followId;
    private Long fromUserId;
    private Long toUserId;
    private LocalDateTime createdAt;

    @Builder
    public FollowResponseDto(Long followId, Long fromUserId, Long toUserId) {
        this.followId = followId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
