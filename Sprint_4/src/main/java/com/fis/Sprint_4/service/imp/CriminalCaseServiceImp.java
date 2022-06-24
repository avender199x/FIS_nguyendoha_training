package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.entity.CriminalCase;
import com.fis.Sprint_4.repository.CriminalCaseRepository;
import com.fis.Sprint_4.service.CriminalCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CriminalCaseServiceImp implements CriminalCaseService {
    private CriminalCaseRepository repository;

    @Autowired
    public CriminalCaseServiceImp(CriminalCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public CriminalCase Save(CriminalCase criminalCase) {
        criminalCase.setCreatedAt(LocalDateTime.now());
        return repository.save(criminalCase);
    }

    @Override
    public CriminalCase update(Long aLong, CriminalCase criminalCase) {
        if (repository.findById(aLong).isPresent()) {
            CriminalCase update = repository.findById(aLong).get();
            update.setDetailedDescription(criminalCase.getDetailedDescription());
            update.setNotes(criminalCase.getNotes());
            update.setLeadInvestigator(criminalCase.getLeadInvestigator());
            update.setNumber(criminalCase.getNumber());
            update.setShortDescription(criminalCase.getShortDescription());
            update.setStatus(criminalCase.getStatus());
            update.setType(criminalCase.getType());
            update.setCreatedAt(criminalCase.getCreatedAt());
            update.setModifiedAt(LocalDateTime.now());
            update.setVersion(criminalCase.getVersion());
            return repository.save(update);
        } else {
            log.error("criminalCase does not exist");
            throw new RuntimeException("criminalCase does not exist");
        }
    }

    @Override
    public Optional<CriminalCase> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public List<CriminalCase> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
