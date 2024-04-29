package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {
}
