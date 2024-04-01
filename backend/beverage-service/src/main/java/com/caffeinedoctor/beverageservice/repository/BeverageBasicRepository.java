package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.BeverageBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeverageBasicRepository extends JpaRepository<BeverageBasic, Long> {
    Optional<BeverageBasic> findByCompany(String company);
    Optional<BeverageBasic> findByName(String name);
}
