package com.caffeinedoctor.beverageservice.Repository;

import com.caffeinedoctor.beverageservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
