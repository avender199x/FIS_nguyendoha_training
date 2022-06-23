package com.fresher.service.Imp;

import com.fresher.model.Person;
import com.fresher.repository.PersonRepo;
import com.fresher.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImp implements PersonService {
    @Autowired
    private PersonRepo repo;

    @Override
    public List<Person> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public Person Save(Person person) {
        repo.save(person);
        return person;
    }

    @Override
    public Person update(Long aLong, Person person) {
        Person update = repo.findById(aLong).get();
        update.setCreatedAt(person.getCreatedAt());
        update.setModifiedAt(LocalDateTime.now());
        update.setFirstName(person.getFirstName());
        update.setHiringDate(person.getHiringDate());
        update.setLastName(person.getLastName());
        update.setNewPassword(person.getNewPassword());
        update.setPassword(person.getPassword());
        update.setUsername(person.getUsername());
        return repo.update(person);
    }

    @Override
    public void delete(Long aLong) {
        repo.deleteById(aLong);
    }
}
