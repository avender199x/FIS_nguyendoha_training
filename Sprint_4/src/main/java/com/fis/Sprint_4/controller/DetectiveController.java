package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.DetectiveErrorException;
import com.fis.Sprint_4.dto.DetectiveDto;
import com.fis.Sprint_4.model.Detective;
import com.fis.Sprint_4.service.DetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detective")
public class DetectiveController {
    @Autowired
    private DetectiveService service;

    @GetMapping("/")
    public List<Detective> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Detective> findByid(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public Detective addPerson(@RequestBody DetectiveDto detective) throws DetectiveErrorException {
        return service.Save(detective);
    }

    @PutMapping("/update/{id}")
    public Detective updatePerson(@PathVariable(name = "id") Long id, @RequestBody DetectiveDto detective)
            throws DetectiveErrorException {
        return service.update(id, detective);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
