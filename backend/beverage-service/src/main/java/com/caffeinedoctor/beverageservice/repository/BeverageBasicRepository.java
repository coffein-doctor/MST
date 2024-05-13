package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.BeverageBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeverageBasicRepository extends JpaRepository<BeverageBasic, Long> {
    List<BeverageBasic> findByCompanyContaining(String word);
    List<BeverageBasic> findByNameContaining(String word);
}
