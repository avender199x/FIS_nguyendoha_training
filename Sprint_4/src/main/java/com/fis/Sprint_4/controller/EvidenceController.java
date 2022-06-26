package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.model.Evidence;
import com.fis.Sprint_4.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evidence")
public class EvidenceController {
    @Autowired
    private EvidenceService service;

    @GetMapping("/")
    public List<Evidence> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Evidence> findByid(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public Evidence addPerson(@RequestBody Evidence evidence) {
        return service.Save(evidence);
    }

    @PutMapping("/update/{id}")
    public Evidence updatePerson(@PathVariable(name = "id") Long id, @RequestBody Evidence evidence) {
        return service.update(id, evidence);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
