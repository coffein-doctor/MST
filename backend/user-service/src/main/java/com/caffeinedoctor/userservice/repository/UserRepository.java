package com.caffeinedoctor.userservice.repository;

import com.caffeinedoctor.userservice.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
