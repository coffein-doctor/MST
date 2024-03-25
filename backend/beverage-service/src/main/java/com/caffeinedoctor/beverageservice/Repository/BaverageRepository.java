package com.caffeinedoctor.beverageservice.Repository;

import com.caffeinedoctor.beverageservice.entity.Baverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaverageRepository extends JpaRepository<Baverage, Long> {
}
