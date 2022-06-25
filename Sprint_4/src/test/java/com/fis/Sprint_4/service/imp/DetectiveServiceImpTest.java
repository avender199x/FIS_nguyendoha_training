package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.core.CaseStatus;
import com.fis.Sprint_4.core.CaseType;
import com.fis.Sprint_4.core.EmploymentStatus;
import com.fis.Sprint_4.core.Rank;
import com.fis.Sprint_4.model.*;
import com.fis.Sprint_4.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetectiveServiceImpTest {
    @Autowired
    private EvidenceService evidenceService;
    @Autowired
    private PersonService personService;
    @Autowired
    private CriminalCaseService criminalCaseService;
    @Autowired
    private DetectiveService detectiveService;
    @Autowired
    private StorageService storageService;


    @Test
    void save() {
        Person person = new Person();
        person.setVersion(1);
        person.setFirstName("do");
        person.setHiringDate(LocalDateTime.now());
        person.setLastName("ha");
        person.setUsername("nguyen do ha");
        person.setPassword("123");
        person.setNewPassword("123");
        personService.Save(person);
        Detective detective = new Detective();
        detective.setArmed(true);
        detective.setBadgeNumber("dsad");
        detective.setRank(Rank.INSPECTOR);
        detective.setStatus(EmploymentStatus.ACTIVE);
        detective.setPerson(personService.findById(1l).get());
        detectiveService.Save(detective);
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setStatus(CaseStatus.SUBMITTED);
        criminalCase.setVersion(4);
        criminalCase.setDetailedDescription("test criminal case");
        criminalCase.setShortDescription("sd");
        criminalCase.setLeadInvestigator(detectiveService.findById(1l).get());
        criminalCase.setNotes("dads");
        criminalCase.setNumber("12");
        criminalCase.setType(CaseType.FELONY);
        criminalCaseService.Save(criminalCase);
        Storage storage = new Storage();
        storage.setLocation("123");
        storage.setName("test storage");
        storageService.Save(storage);

    }

    @Test
    void update() {
      Evidence evidence = new Evidence();
      evidence.setVersion(1);
      evidence.setStorage(storageService.findById(1l).get());
      evidence.setCriminalCase(criminalCaseService.findById(1l).get());
      evidence.setItemName("test evidence");
      evidence.setNumber("12");
      evidence.setNotes("test");
      evidenceService.Save(evidence);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }
}