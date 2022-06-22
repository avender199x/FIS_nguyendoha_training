package com.fresher.repository;

import com.fresher.model.CriminalCase;
import com.fresher.model.Evidence;
import com.fresher.model.Storage;

import java.util.Optional;
import java.util.Set;

public interface EvidenceRepo extends AbstractRepo<Evidence> {
    Set<Evidence> findByCriminalCase(CriminalCase criminalCase);
    Optional<Evidence> findByNumber(String evidenceNumber);
    boolean isInStorage(Storage storage);
}
