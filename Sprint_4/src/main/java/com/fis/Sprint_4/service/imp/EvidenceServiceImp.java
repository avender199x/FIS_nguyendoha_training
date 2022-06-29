package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.EvidenceNotFoundException;
import com.fis.Sprint_4.dto.EvidenceDto;
import com.fis.Sprint_4.model.CriminalCase;
import com.fis.Sprint_4.model.Evidence;
import com.fis.Sprint_4.model.Storage;
import com.fis.Sprint_4.repository.CriminalCaseRepository;
import com.fis.Sprint_4.repository.EvidenceRepository;
import com.fis.Sprint_4.repository.StorageRepository;
import com.fis.Sprint_4.service.EvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EvidenceServiceImp implements EvidenceService {
    @Autowired
    private EvidenceRepository evidenceRepository;
    @Autowired
    private CriminalCaseRepository criminalCaseRepository;
    @Autowired
    private StorageRepository storageRepository;

    @Transactional
    @Override
    public Evidence Save(EvidenceDto evidenceDto) {
        Optional<CriminalCase> criminalCase = criminalCaseRepository.findById(evidenceDto.getCriminalCase());
        Optional<Storage> storage = storageRepository.findById(evidenceDto.getStorage());
        if (criminalCase.isPresent() && storage.isPresent()) {
            Evidence save = new Evidence();
            save.setVersion(evidenceDto.getVersion());
            save.setModifiedAt(LocalDateTime.now());
            save.setCreatedAt(LocalDateTime.now());
            save.setNotes(evidenceDto.getNotes());
            save.setNumber(evidenceDto.getNumber());
            save.setArchived(evidenceDto.getArchived());
            save.setStorage(storage.get());
            save.setItemName(evidenceDto.getItemName());
            save.setCriminalCase(criminalCase.get());
            return evidenceRepository.save(save);
        } else {
            log.error("Save false : \n" + "Time : " + LocalDateTime.now() + "\n EvidenceDto:" + evidenceDto);
            throw new EvidenceNotFoundException("CriminalCase or Storage does not exist");
        }
    }

    @Transactional
    @Override
    public Evidence update(Long aLong, EvidenceDto evidenceDto) {
        Optional<CriminalCase> criminalCase = criminalCaseRepository.findById(evidenceDto.getCriminalCase());
        Optional<Storage> storage = storageRepository.findById(evidenceDto.getStorage());
        Optional<Evidence> update = evidenceRepository.findById(aLong);
        if (update.isPresent() && criminalCase.isPresent() && storage.isPresent()) {
            update.get().setVersion(evidenceDto.getVersion());
            update.get().setModifiedAt(LocalDateTime.now());
            update.get().setNotes(evidenceDto.getNotes());
            update.get().setNumber(evidenceDto.getNumber());
            update.get().setArchived(evidenceDto.getArchived());
            update.get().setStorage(storage.get());
            update.get().setItemName(evidenceDto.getItemName());
            update.get().setCriminalCase(criminalCase.get());
            return evidenceRepository.save(update.get());
        } else {
            log.error("update false :\n" + "Time : " + LocalDateTime.now() + "\n EvidenceId : "
                    + aLong + "\n EvidenceDto : " + evidenceDto);
            throw new EvidenceNotFoundException("Evidence or CriminalCase,Storage does not exist");
        }
    }

    @Override
    public Optional<Evidence> findById(Long aLong) {
        return Optional.ofNullable(evidenceRepository.findById(aLong).orElseThrow(
                () -> {
                    throw new EvidenceNotFoundException("Evidence does not exist");
                }
        ));
    }

    @Override
    public List<Evidence> findAll() {
        return evidenceRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        evidenceRepository.deleteById(aLong);
    }
}
