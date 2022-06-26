package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.model.Person;
import com.fis.Sprint_4.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("/")
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findByid(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public Person addPerson(@RequestBody Person person) {
        return service.Save(person);
    }

    @PutMapping("/update/{id}")
    public Person updatePerson(@PathVariable(name = "id") Long id, @RequestBody Person person) {
        return service.update(id, person);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
