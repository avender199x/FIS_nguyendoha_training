package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.entity.Person;
import com.fis.Sprint_4.repository.PersonRepository;
import com.fis.Sprint_4.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImp implements PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonServiceImp(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person Save(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        return repository.save(person);
    }

    @Override
    public Person update(Long aLong, Person person) {
        if (repository.findById(aLong).isPresent()) {
            Person update = repository.findById(aLong).get();
            update.setModifiedAt(LocalDateTime.now());
            update.setFirstName(person.getFirstName());
            update.setHiringDate(person.getHiringDate());
            update.setLastName(person.getLastName());
            update.setNewPassword(person.getNewPassword());
            update.setPassword(person.getPassword());
            update.setUsername(person.getUsername());
            return repository.save(update);
        } else {
            log.error("person does not exist");
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

    @Override
    public void delete(Long aLong) {
        repository.deleteById(aLong);
    }
}
