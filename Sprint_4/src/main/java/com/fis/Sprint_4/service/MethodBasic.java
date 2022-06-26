package com.fis.Sprint_4.service;

import java.util.List;
import java.util.Optional;

public interface MethodBasic<Dto, Entity, TypeKeyId> {
    public Entity Save(Dto dto);

    public Entity update(TypeKeyId id, Dto dto);

    public Optional<Entity> findById(TypeKeyId id);

    public List<Entity> findAll();

    public void delete(TypeKeyId id);
}
