package com.fresher.service;

import com.fresher.model.Person;
import com.fresher.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonService {
    @Autowired
    private PersonRepo jdbcPersonRepo;

    public void testPersonRepo(){
        Set<Person> personSet = jdbcPersonRepo.findAll();
        System.out.println(personSet);
    }
}
