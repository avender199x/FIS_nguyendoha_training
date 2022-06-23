package com.fresher.service.Imp;

import com.fresher.model.TrackEntry;
import com.fresher.repository.TrackEntryRepo;
import com.fresher.service.TrackEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrackEntryServiceImp implements TrackEntryService {
    @Autowired
    private TrackEntryRepo repo;

    @Override
    public List<TrackEntry> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<TrackEntry> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public TrackEntry Save(TrackEntry trackEntry) {
        repo.save(trackEntry);
        return trackEntry;
    }

    @Override
    public TrackEntry update(Long aLong, TrackEntry trackEntry) {
        TrackEntry update = repo.findById(aLong).get();
        update.setAction(trackEntry.getAction());
        update.setDate(trackEntry.getDate());
        update.setDetective(trackEntry.getDetective());
        update.setEvidence(trackEntry.getEvidence());
        update.setReason(trackEntry.getReason());
        update.setCreatedAt(trackEntry.getCreatedAt());
        update.setModifiedAt(LocalDateTime.now());
        return repo.update(update);
    }

    @Override
    public void delete(Long aLong) {
        repo.deleteById(aLong);
    }
}
