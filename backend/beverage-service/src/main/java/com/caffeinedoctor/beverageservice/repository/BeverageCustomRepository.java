package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.BeverageCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageCustomRepository extends JpaRepository<BeverageCustom, Long> {
}
