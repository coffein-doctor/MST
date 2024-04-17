package com.caffeinedoctor.beverageservice.repository;

import com.caffeinedoctor.beverageservice.entity.DataFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataFileRepository extends JpaRepository<DataFile, Long> {
}
