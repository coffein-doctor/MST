package com.caffeinedoctor.userservice.repository;

import com.caffeinedoctor.userservice.entitiy.Follow;
import com.caffeinedoctor.userservice.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFromUserAndToUser(User fromUser, User toUser);

    // 내가 팔로우하는 사용자 목록
    List<Follow> findAllByFromUserId(Long userId);

    // 나를 팔로우하는 사용자 목록
    List<Follow> findAllByToUserId(Long userId);;

    // 내가 팔로우하는 사용자 수
    long countAllByFromUserId(Long userId);

    // 나를 팔로우하는 사용자 수
    long countAllByToUserId(Long userId);

}
