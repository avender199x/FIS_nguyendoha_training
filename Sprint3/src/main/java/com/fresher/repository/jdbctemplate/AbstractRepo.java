package com.fresher.repository.jdbctemplate;

import com.fresher.core.NotImplementedException;
import com.fresher.model.AbstractEntity;

import java.util.Optional;

public class AbstractRepo<T extends AbstractEntity> implements com.fresher.repository.AbstractRepo<T> {

    @Override
    public void save(T entity) {
        throw new NotImplementedException("Not needed for this implementation.");
        //NOOP
    }

    @Override
    public T update(T entity) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
        //return null;
    }


    @Override
    public void delete(T entity) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public int deleteById(Long entityId) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
        //return 0;
    }

    @Override
    public Optional<T> findById(Long entityId) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
        //return null;
    }
}
