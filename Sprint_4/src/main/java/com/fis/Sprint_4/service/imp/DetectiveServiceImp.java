package com.fis.Sprint_4.service.imp;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.DetectiveErrorException;
import com.fis.Sprint_4.controller.ExceptionHandler.Exception.DetectiveNotFoundException;
import com.fis.Sprint_4.dto.DetectiveDto;
import com.fis.Sprint_4.model.CriminalCase;
import com.fis.Sprint_4.model.Detective;
import com.fis.Sprint_4.model.Person;
import com.fis.Sprint_4.repository.CriminalCaseRepository;
import com.fis.Sprint_4.repository.DetectiveRepository;
import com.fis.Sprint_4.repository.PersonRepository;
import com.fis.Sprint_4.service.DetectiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DetectiveServiceImp implements DetectiveService {
    @Autowired
    private DetectiveRepository detectiveRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Transactional
    @Override
    public Detective Save(DetectiveDto detectiveDto) {
        Optional<Person> person = personRepository.findById(detectiveDto.getPerson());
        if (person.isPresent()) {
            Detective save = new Detective();
            save.setModifiedAt(LocalDateTime.now());
            save.setCreatedAt(LocalDateTime.now());
            save.setPerson(person.get());
            save.setRank(detectiveDto.getRank());
            save.setStatus(detectiveDto.getStatus());
            save.setBadgeNumber(detectiveDto.getBadgeNumber());
            save.setArmed(detectiveDto.getArmed());
            Set<CriminalCase> criminalCases = new HashSet<>();
            detectiveDto.getCriminalCases().stream().forEach(id -> {
                CriminalCase c = criminalCaseRepository.findById(id).get();
                criminalCases.add(c);
            });
            save.setCriminalCases(criminalCases);
            detectiveRepository.save(save);
            return save;
        } else {
            log.error("Save false :\n" + "Time : " + LocalDateTime.now() + "\n DetectiveDto : " + detectiveDto);
            throw new DetectiveErrorException("Person does not exist");
        }
    }

    @Transactional
    @Override
    public Detective update(Long aLong, DetectiveDto detectiveDto) {
        Optional<Detective> update = detectiveRepository.findById(aLong);
        Optional<Person> person = personRepository.findById(detectiveDto.getPerson());
        if (update.isPresent() && person.isPresent()) {
            update.get().setModifiedAt(LocalDateTime.now());
            update.get().setPerson(person.get());
            update.get().setRank(detectiveDto.getRank());
            update.get().setStatus(detectiveDto.getStatus());
            update.get().setBadgeNumber(detectiveDto.getBadgeNumber());
            update.get().setArmed(detectiveDto.getArmed());
            Set<CriminalCase> criminalCases = new HashSet<>();
            detectiveDto.getCriminalCases().stream().forEach(id -> {
                CriminalCase c = criminalCaseRepository.findById(id).get();
                criminalCases.add(c);
            });
            update.get().setCriminalCases(criminalCases);
            return detectiveRepository.save(update.get());
        } else {
            log.error("update false :\n" + "Time : " + LocalDateTime.now()
                    + "\n DetectiveId : " + aLong + "\n DetectiveDto : " + detectiveDto);
            throw new DetectiveErrorException("Detective or Person does not exist");
        }
    }

    @Override
    public Optional<Detective> findById(Long aLong) {
        return Optional.ofNullable(detectiveRepository.findById(aLong).orElseThrow(
                () -> {
                    throw new DetectiveNotFoundException("Detective does not exist");
                }
        ));
    }

    @Override
    public List<Detective> findAll() {
        return detectiveRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        detectiveRepository.deleteById(aLong);
    }
}
