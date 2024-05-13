package com.caffeinedoctor.userservice.repository;

import com.caffeinedoctor.userservice.entitiy.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
}
