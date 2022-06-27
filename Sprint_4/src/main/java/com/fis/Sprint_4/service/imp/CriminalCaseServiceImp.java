package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.dto.CriminalCaseDto;
import com.fis.Sprint_4.model.CriminalCase;
import com.fis.Sprint_4.model.Detective;
import com.fis.Sprint_4.repository.CriminalCaseRepository;
import com.fis.Sprint_4.repository.DetectiveRepository;
import com.fis.Sprint_4.service.CriminalCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CriminalCaseServiceImp implements CriminalCaseService {
    @Autowired
    private CriminalCaseRepository criminalCaseRepository;
    @Autowired
    private DetectiveRepository detectiveRepository;

    @Transactional
    @Override
    public CriminalCase Save(CriminalCaseDto criminalCaseDto) {
        Optional<Detective> detective = detectiveRepository.findById(criminalCaseDto.getLeadInvestigator());
        if (detective.isPresent()) {
            CriminalCase save = new CriminalCase();
            save.setModifiedAt(LocalDateTime.now());
            save.setCreatedAt(LocalDateTime.now());
            save.setType(criminalCaseDto.getType());
            save.setNumber(criminalCaseDto.getNumber());
            save.setStatus(criminalCaseDto.getStatus());
            save.setNotes(criminalCaseDto.getNotes());
            save.setLeadInvestigator(detective.get());
            save.setShortDescription(criminalCaseDto.getShortDescription());
            save.setDetailedDescription(criminalCaseDto.getDetailedDescription());
            save.setAssigned(new HashSet<>());
            for (Long c : criminalCaseDto.getAssigned()) {
                save.getAssigned().add(detectiveRepository.findById(c).get());
            }
            save.setVersion(criminalCaseDto.getVersion());
            return criminalCaseRepository.save(save);
        } else {
            log.error("save false : \n" + "Time : " + LocalDateTime.now() + "\n CriminalCaseDto : " + criminalCaseDto);
            throw new RuntimeException("Detective does not exist");
        }
    }

    @Transactional
    @Override
    public CriminalCase update(Long aLong, CriminalCaseDto criminalCaseDto) {
        Optional<CriminalCase> update = criminalCaseRepository.findById(aLong);
        Optional<Detective> detective = detectiveRepository.findById(criminalCaseDto.getLeadInvestigator());
        if (update.isPresent() && detective.isPresent()) {
            update.get().setModifiedAt(LocalDateTime.now());
            update.get().setType(criminalCaseDto.getType());
            update.get().setNumber(criminalCaseDto.getNumber());
            update.get().setStatus(criminalCaseDto.getStatus());
            update.get().setNotes(criminalCaseDto.getNotes());
            update.get().setLeadInvestigator(detective.get());
            update.get().setShortDescription(criminalCaseDto.getShortDescription());
            update.get().setDetailedDescription(criminalCaseDto.getDetailedDescription());
            update.get().setVersion(criminalCaseDto.getVersion());
            for (Long c : criminalCaseDto.getAssigned()) {
                update.get().getAssigned().add(detectiveRepository.findById(c).get());
            }
            return criminalCaseRepository.save(update.get());
        } else {
            log.error("update false : \n" + "Time : " + LocalDateTime.now()
                    + "\n CriminalCaseId : " + aLong + "\n CriminalCaseDto : " + criminalCaseDto);
            throw new RuntimeException("CriminalCase or Detective does not exist");
        }
    }

    @Override
    public Optional<CriminalCase> findById(Long aLong) {
        return criminalCaseRepository.findById(aLong);
    }

    @Override
    public List<CriminalCase> findAll() {
        return criminalCaseRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        criminalCaseRepository.deleteById(aLong);
    }
}
