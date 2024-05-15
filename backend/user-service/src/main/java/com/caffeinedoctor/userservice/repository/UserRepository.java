package com.caffeinedoctor.userservice.repository;

import com.caffeinedoctor.userservice.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// paRepository는 페이징, 정렬, 쿼리 메서드와 같은 추가 기능을 제공
// public interface UserRepository extends JpaRepository<User, Long> {
// CrudRepository는 기본적인 CRUD 작업만을 제공
@Repository
// CrudRepository<연동 될 entity, entity의 기본키 타입>
public interface UserRepository extends JpaRepository<User, Long> {
    // 같은 이메일 찾기
    Optional<User> findByEmail(String email);

    // 이름 같은 유저 찾기
    Optional<User> findByUsername(String username);

    // 같은 닉네임 찾기
    Optional<User> findByNickname(String nickname);

    // 이메일로 사용자 유무 찾기
    // 이메일이 존재하는지 여부를 확인하는 함수
    boolean existsByEmail(String email);

    // 사용자 이이디로 사용자 유무 찾기
    // username으로 존재하는지 여부를 확인하는 함수
    boolean existsByUsername(String username);

    // nickname 존재 여부를 확인하는 함수
    boolean existsByNickname(String nickname);
}
