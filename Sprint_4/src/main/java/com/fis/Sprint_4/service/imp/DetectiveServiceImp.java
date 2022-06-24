package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.entity.Detective;
import com.fis.Sprint_4.repository.DetectiveRepository;
import com.fis.Sprint_4.service.DetectiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DetectiveServiceImp implements DetectiveService {

    private DetectiveRepository repository;

    @Autowired
    public DetectiveServiceImp(DetectiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Detective Save(Detective detective) {
        detective.setCreatedAt(LocalDateTime.now());
        return repository.save(detective);
    }

    @Override
    public Detective update(Long aLong, Detective detective) {
        if (repository.findById(aLong).isPresent()) {
            Detective update = repository.findById(aLong).get();
            update.setStatus(detective.getStatus());
            update.setCreatedAt(detective.getCreatedAt());
            update.setModifiedAt(LocalDateTime.now());
            update.setArmed(detective.getArmed());
            update.setBadgeNumber(detective.getBadgeNumber());
            update.setPerson(detective.getPerson());
            update.setRank(detective.getRank());
            update.setVersion(detective.getVersion());
            return repository.save(update);
        } else {
            log.error("detective does not exist");
            throw new RuntimeException("detective does not exist");
        }
    }

    @Override
    public Optional<Detective> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public List<Detective> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
