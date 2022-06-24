package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.entity.TrackEntry;
import com.fis.Sprint_4.repository.TrackEntryRepository;
import com.fis.Sprint_4.service.TrackEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackEntryServiceImp implements TrackEntryService {
    private TrackEntryRepository repository;

    @Autowired
    public TrackEntryServiceImp(TrackEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public TrackEntry Save(TrackEntry trackEntry) {
        trackEntry.setCreatedAt(LocalDateTime.now());
        return repository.save(trackEntry);
    }

    @Override
    public TrackEntry update(Long aLong, TrackEntry trackEntry) {
        if (repository.findById(aLong).isPresent()) {
            TrackEntry update = repository.findById(aLong).get();
            update.setVersion(trackEntry.getVersion());
            update.setModifiedAt(LocalDateTime.now());
            update.setAction(trackEntry.getAction());
            update.setDate(trackEntry.getDate());
            update.setDetective(trackEntry.getDetective());
            update.setEvidence(trackEntry.getEvidence());
            update.setReason(trackEntry.getReason());
            return repository.save(update);
        } else {
            log.error("trackEntry does not exist");
            throw new RuntimeException("trackEntry does not exist");
        }
    }

    @Override
    public Optional<TrackEntry> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public List<TrackEntry> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
