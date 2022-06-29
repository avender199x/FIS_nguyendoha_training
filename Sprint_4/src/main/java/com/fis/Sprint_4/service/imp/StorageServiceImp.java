package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.StorageNotFoundException;
import com.fis.Sprint_4.dto.StorageDto;
import com.fis.Sprint_4.model.Storage;
import com.fis.Sprint_4.repository.StorageRepository;
import com.fis.Sprint_4.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StorageServiceImp implements StorageService {
    @Autowired
    private StorageRepository repository;

    @Transactional
    @Override
    public Storage Save(StorageDto storageDto) {
        Storage save = new Storage();
        save.setModifiedAt(LocalDateTime.now());
        save.setCreatedAt(LocalDateTime.now());
        save.setVersion(storageDto.getVersion());
        save.setLocation(storageDto.getLocation());
        save.setName(storageDto.getName());
        return repository.save(save);
    }

    @Transactional
    @Override
    public Storage update(Long aLong, StorageDto storageDto) {
        Optional<Storage> update = repository.findById(aLong);
        if (update.isPresent()) {
            update.get().setModifiedAt(LocalDateTime.now());
            update.get().setVersion(storageDto.getVersion());
            update.get().setLocation(storageDto.getLocation());
            update.get().setName(storageDto.getName());
            return repository.save(update.get());
        } else {
            log.error("update false : \n" + "Time : " + LocalDateTime.now() + " \n storageId : " + aLong);
            throw new StorageNotFoundException("storage does not exist");
        }
    }

    @Override
    public Optional<Storage> findById(Long aLong) {

        return Optional.ofNullable(repository.findById(aLong).orElseThrow(
                () -> {
                    throw new StorageNotFoundException("storage does not exist");
                }
        ));
    }

    @Override
    public List<Storage> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
