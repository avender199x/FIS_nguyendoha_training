package com.fresher.service.Imp;

import com.fresher.model.Person;
import com.fresher.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceImpTest {
    // JPA test
    @Autowired
    private PersonService service;

    @Test
    void findAll() {
        service.findAll();
    }

    @Test
    void findById() {
        System.out.println(service.findById(1l).get());

    }

    @Test
    void save() {
        Person person = new Person();
        person.setPassword("12312");
        person.setUsername("Ha");
        person.setLastName("ad");
        person.setFirstName("test");
        service.Save(person);
    }

    @Test
    void update() {
        Person person = new Person();
        person.setPassword("12312");
        person.setUsername("Ha");
        person.setLastName("do");
        person.setFirstName("do");
        System.out.println(service.update(1l, person));
    }

    @Test
    void delete() {
        service.delete(1l);
    }
}