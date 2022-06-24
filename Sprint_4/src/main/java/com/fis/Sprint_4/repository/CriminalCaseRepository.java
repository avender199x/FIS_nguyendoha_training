package com.fis.Sprint_4.repository;

import com.fis.Sprint_4.entity.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalCaseRepository extends JpaRepository<CriminalCase, Long> {
}
