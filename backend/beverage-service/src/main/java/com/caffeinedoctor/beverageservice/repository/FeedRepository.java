package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
