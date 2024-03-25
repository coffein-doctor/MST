package com.caffeinedoctor.userservice.repository;

import com.caffeinedoctor.userservice.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

// paRepository는 페이징, 정렬, 쿼리 메서드와 같은 추가 기능을 제공
// public interface UserRepository extends JpaRepository<User, Long> {
// CrudRepository는 기본적인 CRUD 작업만을 제공
public interface UserRepository extends CrudRepository<User, Long> {
    // CrudRepository<연동 될 entity, entity의 기본키 타입>
}
