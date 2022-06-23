package com.fresher.repository.Jpa;

import com.fresher.core.CaseStatus;
import com.fresher.core.CaseType;
import com.fresher.model.CriminalCase;
import com.fresher.model.Detective;
import com.fresher.repository.CriminalCaseRepo;
import com.fresher.repository.jdbctemplate.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Repository
public class JPACriminalCaseRepo extends AbstractRepo<CriminalCase>
        implements CriminalCaseRepo {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<CriminalCase> findById(Long id) {
        return Optional.of(entityManager.find(CriminalCase.class, id));
    }

    @Override
    public Set<CriminalCase> findAll() {
        TypedQuery<CriminalCase> query = entityManager.createQuery("select c from CriminalCase c", CriminalCase.class);
        return (Set<CriminalCase>) query.getResultList();
    }

    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        //ToDo . . .
        return null;
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        CriminalCase criminalCase =
                entityManager.createQuery("select c from CriminalCase c where c.number =:caseNumber", CriminalCase.class)
                        .setParameter("caseNumber", caseNumber).getSingleResult();
        return criminalCase != null ? Optional.of(criminalCase) : Optional.empty();
    }

    @Transactional
    @Override
    public CriminalCase updateCriminalCase(CriminalCase cc) {
        CriminalCase check = entityManager.find(CriminalCase.class, cc.getId());
        if (check != null) {
            return entityManager.merge(cc);
        } else {
            throw new RuntimeException("CriminalCase null");
        }
    }

    @Transactional
    @Override
    public void save(CriminalCase cc) {
        entityManager.persist(cc);
    }

    @Transactional
    @Override
    public void delete(CriminalCase entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public int deleteById(Long entityId) {
        CriminalCase criminalCase = entityManager.find(CriminalCase.class, entityId);
        entityManager.remove(criminalCase);
        return entityId.intValue();
    }

    @Override
    public Set<CriminalCase> findByStatus(CaseStatus status) {
        return (Set<CriminalCase>)
                entityManager.
                        createQuery("select c from CriminalCase c where c.status =:status").
                        setParameter("status", status).getResultList();
    }

    @Override
    public Set<CriminalCase> findByType(CaseType type) {
        return (Set<CriminalCase>)
                entityManager.
                        createQuery("select c from CriminalCase c where c.type =:type").
                        setParameter("type", type).getResultList();
    }
}
