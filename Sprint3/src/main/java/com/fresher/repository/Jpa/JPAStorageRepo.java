package com.fresher.repository.Jpa;

import com.fresher.model.Storage;
import com.fresher.repository.StorageRepo;
import com.fresher.repository.jdbctemplate.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAStorageRepo extends AbstractRepo<Storage> implements StorageRepo {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Storage entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public Storage update(Storage entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Storage entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public int deleteById(Long entityId) {
        Storage storage = entityManager.find(Storage.class, entityId);
        entityManager.remove(storage);
        return entityId.intValue();
    }

    @Override
    public Optional<Storage> findById(Long entityId) {
        return Optional.of(entityManager.find(Storage.class, entityId));
    }

    @Override
    public List<Storage> findAll() {
        return entityManager.createQuery("select s from Storage s").getResultList();
    }
}
