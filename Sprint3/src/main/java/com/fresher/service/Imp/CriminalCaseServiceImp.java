package com.fresher.service.Imp;

import com.fresher.model.CriminalCase;
import com.fresher.repository.Jpa.JPACriminalCaseRepo;
import com.fresher.service.CriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CriminalCaseServiceImp implements CriminalCaseService {
    @Autowired
    private JPACriminalCaseRepo repo;

    @Override
    public List<CriminalCase> findAll() {
        return (List<CriminalCase>) repo.findAll();
    }

    @Override
    public Optional<CriminalCase> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public CriminalCase Save(CriminalCase criminalCase) {
        repo.save(criminalCase);
        return criminalCase;
    }

    @Override
    public CriminalCase update(Long aLong, CriminalCase criminalCase) {
        CriminalCase update = findById(aLong).get();
        update.setDetailedDescription(criminalCase.getDetailedDescription());
        update.setNotes(criminalCase.getNotes());
        update.setNumber(criminalCase.getNumber());
        update.setLeadInvestigator(criminalCase.getLeadInvestigator());
        update.setType(criminalCase.getType());
        update.setShortDescription(criminalCase.getShortDescription());
        update.setCreatedAt(criminalCase.getCreatedAt());
        update.setModifiedAt(LocalDateTime.now());
        update.setStatus(criminalCase.getStatus());
        repo.update(criminalCase);
        return criminalCase;
    }

    @Override
    public void delete(Long aLong) {
        repo.deleteById(aLong);
    }
}
