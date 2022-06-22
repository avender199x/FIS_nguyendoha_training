package com.fresher.repository.jdbctemplate;

import com.fresher.core.NotImplementedException;
import com.fresher.model.AbstractEntity;
import com.fresher.repository.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class JdbcAbstractRepo<T extends AbstractEntity> implements AbstractRepo<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public JdbcAbstractRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
