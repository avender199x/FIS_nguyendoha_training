package com.fis.Sprint_4.repository;

import com.fis.Sprint_4.entity.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
}
