package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.model.Storage;
import com.fis.Sprint_4.repository.StorageRepository;
import com.fis.Sprint_4.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StorageServiceImp implements StorageService {
    private StorageRepository repository;

    @Autowired
    public StorageServiceImp(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Storage Save(Storage storage) {
        storage.setCreatedAt(LocalDateTime.now());
        storage.setModifiedAt(LocalDateTime.now());
        return repository.save(storage);
    }

    @Override
    public Storage update(Long aLong, Storage storage) {
        if (repository.findById(aLong).isPresent()) {
            Storage update = repository.findById(aLong).get();
            update.setModifiedAt(LocalDateTime.now());
            update.setLocation(storage.getLocation());
            update.setName(storage.getName());
            return repository.save(update);
        } else {
            log.error("storage does not exist");
            throw new RuntimeException("storage does not exist");
        }
    }

    @Override
    public Optional<Storage> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public List<Storage> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
