package com.fresher.repository;

import com.fresher.model.CriminalCase;
import com.fresher.model.Evidence;
import com.fresher.model.Storage;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EvidenceRepo extends AbstractRepo<Evidence> {
    public List<Evidence> findAll();
}
