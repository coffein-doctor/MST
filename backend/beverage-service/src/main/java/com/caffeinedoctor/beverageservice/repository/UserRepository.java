package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
