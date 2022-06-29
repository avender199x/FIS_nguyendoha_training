package com.fis.Sprint_4.service;

import com.fis.Sprint_4.controller.ExceptionHandler.Exception.DetectiveErrorException;

import java.util.List;
import java.util.Optional;

public interface MethodBasic<Dto, Entity, TypeKeyId> {
    public Entity Save(Dto dto) throws DetectiveErrorException;

    public Entity update(TypeKeyId id, Dto dto) throws DetectiveErrorException;

    public Optional<Entity> findById(TypeKeyId id);

    public List<Entity> findAll();

    public void delete(TypeKeyId id);
}
