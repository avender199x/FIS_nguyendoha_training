package com.fresher.service.Imp;

import com.fresher.model.Detective;
import com.fresher.repository.DetectiveRepo;
import com.fresher.service.DetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DetectiveServiceImp implements DetectiveService {
    @Autowired
    private DetectiveRepo repo;

    @Override
    public List<Detective> findAll() {
        return (List<Detective>) repo.findAll();
    }

    @Override
    public Optional<Detective> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public Detective Save(Detective detective) {
        repo.save(detective);
        return detective;
    }

    @Override
    public Detective update(Long aLong, Detective detective) {
        Detective update = repo.findById(aLong).get();
        update.setArmed(detective.getArmed());
        update.setStatus(detective.getStatus());
        update.setBadgeNumber(detective.getBadgeNumber());
        update.setRank(detective.getRank());
        update.setModifiedAt(LocalDateTime.now());
        update.setPerson(detective.getPerson());
        return repo.update(detective);
    }

    @Override
    public void delete(Long aLong) {
        repo.deleteById(aLong);
    }
}
