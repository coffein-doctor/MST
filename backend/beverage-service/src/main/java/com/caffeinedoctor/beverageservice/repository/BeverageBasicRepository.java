package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.BeverageBasic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageBasicRepository extends JpaRepository<BeverageBasic, Long> {
}
