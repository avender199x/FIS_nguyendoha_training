package com.fresher.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MethodBasic<T, TypeKeyId> {
    public Set<T> findAll();

    public Optional<T> findById(TypeKeyId id);

    public T Save(T t);

    public T update(TypeKeyId id, T t);

    public void delete(TypeKeyId id);
}
