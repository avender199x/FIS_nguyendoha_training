package com.fis.Sprint_4.service;

import java.util.List;
import java.util.Optional;

public interface MethodBasic<T, TypeKeyId> {
    public T Save(T t);

    public T update(TypeKeyId id, T t);

    public Optional<T> findById(TypeKeyId id);

    public List<T> findAll();

    public void delete(TypeKeyId id);
}
