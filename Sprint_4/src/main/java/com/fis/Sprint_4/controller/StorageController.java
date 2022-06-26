package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.model.Storage;
import com.fis.Sprint_4.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService service;

    @GetMapping("/")
    public List<Storage> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Storage> findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public Storage addStorage(@RequestBody Storage storage) {
        return service.Save(storage);
    }

    @PutMapping("/update/{id}")
    public Storage updateStorage(@PathVariable(name = "id") Long id, @RequestBody Storage storage) {
        return service.update(id, storage);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStorage(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
