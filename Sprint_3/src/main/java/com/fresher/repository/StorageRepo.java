package com.fresher.repository;

import com.fresher.model.Storage;

import java.util.Optional;

public interface StorageRepo extends AbstractRepo<Storage> {

    Optional<Storage> findByName(String name);

    Optional<Storage> findByLocation(String location);
}
