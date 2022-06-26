package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.model.CriminalCase;
import com.fis.Sprint_4.service.CriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/criminal_case")
public class CriminalCaseController {
    @Autowired
    private CriminalCaseService service;

    @GetMapping("/")
    public List<CriminalCase> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CriminalCase> findByid(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public CriminalCase addPerson(@RequestBody CriminalCase criminalCase) {
        return service.Save(criminalCase);
    }

    @PutMapping("/update/{id}")
    public CriminalCase updatePerson(@PathVariable(name = "id") Long id, @RequestBody CriminalCase criminalCase) {
        return service.update(id, criminalCase);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

}
