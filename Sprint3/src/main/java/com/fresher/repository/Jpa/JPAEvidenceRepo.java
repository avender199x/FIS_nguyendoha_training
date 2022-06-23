package com.fresher.repository.Jpa;

import com.fresher.model.Evidence;
import com.fresher.repository.EvidenceRepo;
import com.fresher.repository.jdbctemplate.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAEvidenceRepo extends AbstractRepo<Evidence> implements EvidenceRepo {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Evidence entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public Evidence update(Evidence entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Evidence entity) {
        entityManager.remove(entity);
        super.delete(entity);
    }

    @Transactional
    @Override
    public int deleteById(Long entityId) {
        Evidence evidence = entityManager.find(Evidence.class, entityId);
        entityManager.remove(evidence);
        return entityId.intValue();
    }

    @Override
    public Optional<Evidence> findById(Long entityId) {
        return Optional.of(entityManager.find(Evidence.class, entityId));
    }

    @Override
    public List<Evidence> findAll() {

        return entityManager.createQuery("select e from Evidence e").getResultList();
    }
}
