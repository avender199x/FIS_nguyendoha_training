package com.fis.Sprint_4.controller;

import com.fis.Sprint_4.model.Evidence;
import com.fis.Sprint_4.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CriminalEvidenceController {
    private EvidenceService evidenceService;

    @Autowired
    public CriminalEvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @GetMapping("/")
    public List<Evidence> findByAll() {
        return evidenceService.findAll();
    }
}
