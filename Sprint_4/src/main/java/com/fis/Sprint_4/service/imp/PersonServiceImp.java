package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.dto.PersonDto;
import com.fis.Sprint_4.model.Person;
import com.fis.Sprint_4.repository.PersonRepository;
import com.fis.Sprint_4.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImp implements PersonService {
    @Autowired
    private PersonRepository repository;

    @Transactional
    @Override
    public Person Save(PersonDto personDto) {
        Person save = new Person();
        save.setVersion(personDto.getVersion());
        save.setCreatedAt(LocalDateTime.now());
        save.setModifiedAt(LocalDateTime.now());
        save.setPassword(personDto.getPassword());
        save.setUsername(personDto.getUsername());
        save.setLastName(personDto.getLastName());
        save.setHiringDate(personDto.getHiringDate());
        save.setFirstName(personDto.getFirstName());
        return repository.save(save);
    }

    @Transactional
    @Override
    public Person update(Long aLong, PersonDto personDto) {
        if (repository.findById(aLong).isPresent()) {
            Person update = repository.findById(aLong).get();
            update.setVersion(personDto.getVersion());
            update.setCreatedAt(LocalDateTime.now());
            update.setModifiedAt(LocalDateTime.now());
            update.setNewPassword(update.getNewPassword());
            update.setUsername(personDto.getUsername());
            update.setLastName(personDto.getLastName());
            update.setHiringDate(personDto.getHiringDate());
            update.setFirstName(personDto.getFirstName());
            return repository.save(update);
        } else {
            log.error("update false :\n" + "Time : " + LocalDateTime.now() + "\nPersonId : " + aLong);
            throw new RuntimeException("person does not exist");
        }
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
