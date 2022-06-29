package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.TrackEntryErrorException;
import com.fis.Sprint_4.controller.ExceptionHandler.Exception.TrackEntryNotFoundException;
import com.fis.Sprint_4.dto.TrackEntryDto;
import com.fis.Sprint_4.model.Detective;
import com.fis.Sprint_4.model.Evidence;
import com.fis.Sprint_4.model.TrackEntry;
import com.fis.Sprint_4.repository.DetectiveRepository;
import com.fis.Sprint_4.repository.EvidenceRepository;
import com.fis.Sprint_4.repository.TrackEntryRepository;
import com.fis.Sprint_4.service.TrackEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackEntryServiceImp implements TrackEntryService {
    @Autowired
    private TrackEntryRepository repository;
    @Autowired
    private DetectiveRepository detectiveRepository;
    @Autowired
    private EvidenceRepository evidenceRepository;

    @Transactional
    @Override
    public TrackEntry Save(TrackEntryDto trackEntryDto) throws TrackEntryErrorException {
        Optional<Detective> detective = detectiveRepository.findById(trackEntryDto.getDetective());
        Optional<Evidence> evidence = evidenceRepository.findById(trackEntryDto.getEvidence());
        if (detective.isPresent() && evidence.isPresent()) {
            TrackEntry save = new TrackEntry();
            save.setVersion(trackEntryDto.getVersion());
            save.setAction(trackEntryDto.getAction());
            save.setDate(trackEntryDto.getDate());
            save.setDetective(detective.get());
            save.setEvidence(evidence.get());
            save.setCreatedAt(LocalDateTime.now());
            save.setModifiedAt(LocalDateTime.now());
            save.setReason(trackEntryDto.getReason());
            return repository.save(save);
        } else {
            log.error("Save false :\n Time : " + LocalDateTime.now() + ", \nTrackEntryDto : " + trackEntryDto);
            throw new TrackEntryErrorException("detective or evidence does not exist");
        }

    }

    @Transactional
    @Override
    public TrackEntry update(Long aLong, TrackEntryDto trackEntryDto) throws TrackEntryErrorException {
        Optional<TrackEntry> update = repository.findById(aLong);
        Optional<Detective> detective = detectiveRepository.findById(trackEntryDto.getDetective());
        Optional<Evidence> evidence = evidenceRepository.findById(trackEntryDto.getEvidence());
        if (update.isPresent() && detective.isPresent() && evidence.isPresent()) {
            update.get().setVersion(trackEntryDto.getVersion());
            update.get().setAction(trackEntryDto.getAction());
            update.get().setDate(trackEntryDto.getDate());
            update.get().setDetective(detective.get());
            update.get().setEvidence(evidence.get());
            update.get().setCreatedAt(trackEntryDto.getCreatedAt());
            update.get().setModifiedAt(LocalDateTime.now());
            update.get().setReason(trackEntryDto.getReason());
            return repository.save(update.get());
        } else {
            log.error("Update false :\n Time : " + LocalDateTime.now() + ", \nTrackEntryDto : "
                    + trackEntryDto + "\nTrackEntry Id : " + aLong);
            throw new TrackEntryErrorException("TrackEntry or detective,TrackEntry does not exist");
        }
    }

    @Override
    public Optional<TrackEntry> findById(Long aLong) {

        return Optional.ofNullable(repository.findById(aLong).orElseThrow(
                () -> {
                    throw new TrackEntryNotFoundException("TrackEntry does not exist");
                }
        ));
    }

    @Override
    public List<TrackEntry> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
