package com.fresher.repository;

import com.fresher.model.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageRepo extends AbstractRepo<Storage> {
    public List<Storage> findAll();
}
