package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.entity.Evidence;
import com.fis.Sprint_4.repository.EvidenceRepository;
import com.fis.Sprint_4.service.EvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EvidenceServiceImp implements EvidenceService {

    private EvidenceRepository repository;

    @Autowired
    public EvidenceServiceImp(EvidenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Evidence Save(Evidence evidence) {
        evidence.setCreatedAt(LocalDateTime.now());
        return repository.save(evidence);
    }

    @Override
    public Evidence update(Long aLong, Evidence evidence) {
        if (repository.findById(aLong).isPresent()) {
            Evidence update = repository.findById(aLong).get();
            update.setVersion(evidence.getVersion());
            update.setNotes(evidence.getNotes());
            update.setNumber(evidence.getNumber());
            update.setModifiedAt(evidence.getModifiedAt());
            update.setArchived(evidence.getArchived());
            update.setCriminalCase(evidence.getCriminalCase());
            update.setStorage(evidence.getStorage());
            update.setItemName(evidence.getItemName());
            return repository.save(update);
        } else {
            log.error("evidence does not exist");
            throw new RuntimeException("evidence does not exist");
        }
    }

    @Override
    public Optional<Evidence> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public List<Evidence> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
