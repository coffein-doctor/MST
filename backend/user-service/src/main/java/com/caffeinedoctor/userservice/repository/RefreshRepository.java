package com.caffeinedoctor.userservice.repository;

import com.caffeinedoctor.userservice.entitiy.Refresh;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<Refresh, Long> {

    Boolean existsByRefreshToken(String refresh);

    // 사용자 이름을 기준으로 모든 리프레쉬 토큰을 삭제하는 메소드
    @Transactional
    void deleteByUsername(String username);

    @Transactional
    void deleteByRefreshToken(String refresh);
}