package com.fresher.repository.Jpa;

import com.fresher.model.TrackEntry;
import com.fresher.repository.TrackEntryRepo;
import com.fresher.repository.jdbctemplate.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JPATrackEntryRepo extends AbstractRepo<TrackEntry> implements TrackEntryRepo {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(TrackEntry entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public TrackEntry update(TrackEntry entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(TrackEntry entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public int deleteById(Long entityId) {
        TrackEntry trackEntry = entityManager.find(TrackEntry.class, entityId);
        entityManager.remove(trackEntry);
        return entityId.intValue();
    }

    @Override
    public Optional<TrackEntry> findById(Long entityId) {
        return Optional.of(entityManager.find(TrackEntry.class, entityId));
    }

    @Override
    public List<TrackEntry> findAll() {
        return entityManager.createQuery("select t from TrackEntry t").getResultList();
    }
}
