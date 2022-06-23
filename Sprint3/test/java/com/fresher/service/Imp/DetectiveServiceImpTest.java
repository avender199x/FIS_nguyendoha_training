package com.fresher.service.Imp;

import com.fresher.core.EmploymentStatus;
import com.fresher.core.Rank;
import com.fresher.model.Detective;
import com.fresher.model.Person;
import com.fresher.service.DetectiveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetectiveServiceImpTest {
    //Jdbc Test
    @Autowired
    private DetectiveService service;

    @Test
    void findAll() {
        service.findAll();
    }

    @Test
    void findById() {
        service.findById(1l);
    }

    @Test
    void save() {
        Person person = new Person();
        person.setFirstName("s");
        Detective detective = new Detective();
        detective.setArmed(true);
        detective.setPerson(person);
        detective.setRank(Rank.TRAINEE);
        detective.setStatus(EmploymentStatus.ACTIVE);
        detective.setBadgeNumber("abc");
        detective.setCreatedAt(LocalDateTime.now());
        service.Save(detective);
    }

    @Test
    void update() {
        Detective detective = service.findById(1l).get();
        detective.setStatus(EmploymentStatus.VACATION);
        service.update(1l, detective);
    }

    @Test
    void delete() {
        service.delete(1l);
    }
}