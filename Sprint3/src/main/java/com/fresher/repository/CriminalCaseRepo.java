package com.fresher.repository;

import com.fresher.core.CaseStatus;
import com.fresher.core.CaseType;
import com.fresher.model.CriminalCase;
import com.fresher.model.Detective;

import java.util.Optional;
import java.util.Set;

public interface CriminalCaseRepo extends  AbstractRepo<CriminalCase>  {
    Set<CriminalCase> findByLeadInvestigator(Detective detective);
    Optional<CriminalCase> findByNumber(String caseNumber);
    Set<CriminalCase> findByStatus(CaseStatus status);
    Set<CriminalCase> findByType(CaseType type);
}
