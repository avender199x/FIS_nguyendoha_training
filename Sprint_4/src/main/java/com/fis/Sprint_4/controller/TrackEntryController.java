package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.model.TrackEntry;
import com.fis.Sprint_4.service.TrackEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/track_entry")
public class TrackEntryController {
    @Autowired
    private TrackEntryService service;

    @GetMapping("/")
    public List<TrackEntry> findAll() {

        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TrackEntry> findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public TrackEntry addStorage(@RequestBody TrackEntry trackEntry) {
        return service.Save(trackEntry);
    }

    @PutMapping("/update/{id}")
    public TrackEntry updateStorage(@PathVariable(name = "id") Long id, @RequestBody TrackEntry trackEntry) {
        return service.update(id, trackEntry);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStorage(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

}
