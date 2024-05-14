package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.dto.request.follow.FollowRequestDto;
import com.caffeinedoctor.userservice.dto.response.FollowResponseDto;
import com.caffeinedoctor.userservice.entitiy.Follow;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.enums.UserStatus;
import com.caffeinedoctor.userservice.repository.FollowRepository;
import com.caffeinedoctor.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // (성능 최적화 - 읽기 전용에만 사용) 데이터 변경이면 @Transactional 붙여야한다.
@RequiredArgsConstructor
@Slf4j
public class FollowServiceImpl implements FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    @Override
    public FollowResponseDto createFollow(FollowRequestDto requestDto) {

        // 팔로우 하는 유저
        User fromUser = userRepository.findById(requestDto.getFromUserId())
                .orElseThrow(() -> new IllegalArgumentException("FromUser not found. id=" + requestDto.getFromUserId()));

        if (fromUser.getStatus() == UserStatus.NEW_USER) {
            throw new IllegalArgumentException(fromUser.getNickname() + " has not yet completed the registration.");
        }

        // 팔로우 받는 유저
        User toUser = userRepository.findById(requestDto.getToUserId())
                .orElseThrow(() -> new IllegalArgumentException("ToUser not found. id=" + requestDto.getToUserId()));

        if (toUser.getStatus() == UserStatus.NEW_USER) {
            throw new IllegalStateException(toUser.getNickname() + " has not yet completed the registration.");
        }

        // 중복 체크: 이미 팔로우 관계가 존재하는지 확인
        boolean exists = followRepository.existsByFromUserAndToUser(fromUser, toUser);
        if (exists) {
            throw new IllegalStateException("Follow relationship already exists.");
        }

        // 팔로우 엔티티 생성
        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

        followRepository.save(follow);

        // 팔로우 관계 저장
        return FollowResponseDto.builder()
                .followId(follow.getId())
                .fromUserId(fromUser.getId())
                .toUserId(toUser.getId())
                .build();
    }

}
