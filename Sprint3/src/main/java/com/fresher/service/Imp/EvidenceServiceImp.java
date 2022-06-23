package com.fresher.service.Imp;

import com.fresher.model.Evidence;
import com.fresher.repository.EvidenceRepo;
import com.fresher.repository.Jpa.JPAEvidenceRepo;
import com.fresher.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EvidenceServiceImp implements EvidenceService {
    @Autowired
    private EvidenceRepo repo;

    @Override
    public List<Evidence> findAll() {

        return repo.findAll();
    }

    @Override
    public Optional<Evidence> findById(Long aLong) {

        return repo.findById(aLong);
    }

    @Override
    public Evidence Save(Evidence evidence) {
        repo.save(evidence);
        return evidence;
    }

    @Override
    public Evidence update(Long aLong, Evidence evidence) {
        Evidence update = findById(aLong).get();
        update.setNotes(evidence.getNotes());
        update.setNumber(evidence.getNumber());
        update.setModifiedAt(LocalDateTime.now());
        update.setCreatedAt(evidence.getCreatedAt());
        update.setArchived(evidence.getArchived());
        update.setItemName(evidence.getItemName());
        update.setStorage(evidence.getStorage());
        update.setCriminalCase(evidence.getCriminalCase());
        return repo.update(update);
    }

    @Override
    public void delete(Long aLong) {
        repo.deleteById(aLong);
    }
}
