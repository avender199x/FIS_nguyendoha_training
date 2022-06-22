package com.fresher.repository.jdbctemplate;

import com.fresher.core.CaseStatus;
import com.fresher.core.CaseType;
import com.fresher.core.NotImplementedException;
import com.fresher.model.CriminalCase;
import com.fresher.model.Detective;
import com.fresher.repository.CriminalCaseRepo;
import com.fresher.repository.jdbctemplate.mapper.CriminalCaseRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcCriminalCaseRepo extends JdbcAbstractRepo<CriminalCase>
        implements CriminalCaseRepo {
    private RowMapper<CriminalCase> rowMapper = new CriminalCaseRowMapper();
    private EntityManager entityManager;

    public JdbcCriminalCaseRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<CriminalCase> findById(Long id) {
        return Optional.of(entityManager.find(CriminalCase.class, id));
    }

    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        String sql = "select ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION from CRIMINAL_CASE c, DETECTIVE d where c.LEAD_INVESTIGATOR=d.ID and d.ID= ?";
        return new HashSet<>(jdbcTemplate.query(sql, new Object[]{detective.getId()}, rowMapper));
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        CriminalCase criminalCase =
                entityManager.createQuery("select c from CriminalCase c where c.number =:casenumber", CriminalCase.class)
                        .setParameter("casenumber", caseNumber).getSingleResult();
        return criminalCase != null ? Optional.of(criminalCase) : Optional.empty();
    }

    @Transactional
    @Override
    public CriminalCase updateCriminalCase(CriminalCase cc) {
        CriminalCase check = entityManager.find(CriminalCase.class, cc.getId());
        if (check != null) {
            return entityManager.merge(cc);
        } else {
            throw new RuntimeException("criminal case null");
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

        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public Set<CriminalCase> findByType(CaseType type) {
        throw new NotImplementedException("Not needed for this implementation.");
    }
}
