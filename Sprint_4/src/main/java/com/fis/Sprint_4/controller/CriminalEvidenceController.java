package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.model.Evidence;
import com.fis.Sprint_4.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class CriminalEvidenceController {
    private EvidenceService evidenceService;

    @Autowired
    public CriminalEvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @GetMapping("/{id}")
    public Optional<Evidence> findById(@PathVariable(name = "id") Long id) {
        return evidenceService.findById(id);
    }
}
