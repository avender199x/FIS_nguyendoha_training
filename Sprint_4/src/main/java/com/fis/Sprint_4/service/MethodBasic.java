package com.fis.Sprint_4.service;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.CriminalCaseErrorException;
import com.fis.Sprint_4.controller.ExceptionHandler.Exception.DetectiveErrorException;
import com.fis.Sprint_4.controller.ExceptionHandler.Exception.EvidenceErrorException;
import com.fis.Sprint_4.controller.ExceptionHandler.Exception.TrackEntryErrorException;

import java.util.List;
import java.util.Optional;

public interface MethodBasic<Dto, Entity, TypeKeyId> {
    public Entity Save(Dto dto) throws DetectiveErrorException, TrackEntryErrorException, CriminalCaseErrorException, EvidenceErrorException;

    public Entity update(TypeKeyId id, Dto dto) throws DetectiveErrorException, TrackEntryErrorException, CriminalCaseErrorException, EvidenceErrorException;

    public Optional<Entity> findById(TypeKeyId id);

    public List<Entity> findAll();

    public void delete(TypeKeyId id);
}
