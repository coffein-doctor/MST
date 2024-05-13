package com.caffeinedoctor.userservice.service;

import com.caffeinedoctor.userservice.entitiy.Follow;
import com.caffeinedoctor.userservice.entitiy.User;
import com.caffeinedoctor.userservice.repository.FollowRepository;
import com.caffeinedoctor.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Follow createFollow(Long followerId, Long followingId) {
        // followerId는 팔로우하는 사용자의 ID, followingId는 팔로우되는 사용자의 ID
        // 팔로워 사용자를 찾음
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("Follower not found"));

        // 팔로잉 사용자를 찾음
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new EntityNotFoundException("Following not found"));

        // 팔로우 엔티티 생성
        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        // 팔로우 관계 저장
        return followRepository.save(follow);

    }


}
