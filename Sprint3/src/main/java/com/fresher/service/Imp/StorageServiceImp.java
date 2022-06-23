package com.fresher.service.Imp;

import com.fresher.model.Storage;
import com.fresher.repository.StorageRepo;
import com.fresher.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImp implements StorageService {
    @Autowired
    private StorageRepo repo;

    @Override
    public List<Storage> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Storage> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public Storage Save(Storage storage) {
        repo.save(storage);
        return storage;
    }

    @Override
    public Storage update(Long aLong, Storage storage) {
        Storage update = repo.findById(aLong).get();
        update.setLocation(storage.getLocation());
        update.setName(storage.getName());
        update.setCreatedAt(storage.getCreatedAt());
        update.setModifiedAt(LocalDateTime.now());
        return repo.update(update);
    }

    @Override
    public void delete(Long aLong) {
        repo.deleteById(aLong);
    }
}
