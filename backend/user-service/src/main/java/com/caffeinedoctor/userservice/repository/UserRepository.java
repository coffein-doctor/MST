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
public interface UserRepository extends CrudRepository<User, Long> {
    // 같은 이메일 찾기
    Optional findByEmail(String email);

    // 같은 닉네임 찾기
    Optional findByNickname(String nickname);

    // 이메일로 사용자 유무 찾기
    // 이메일이 존재하는지 여부를 확인하는 함수
    boolean existsByEmail(String email);
}
