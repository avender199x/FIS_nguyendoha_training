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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcCriminalCaseRepo  extends JdbcAbstractRepo<CriminalCase>
        implements CriminalCaseRepo {
    private RowMapper<CriminalCase> rowMapper = new CriminalCaseRowMapper();

    public JdbcCriminalCaseRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<CriminalCase> findById(Long id) {
        String sql = "select ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION from CRIMINAL_CASE where ID= ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        String sql =  "select ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION from CRIMINAL_CASE c, DETECTIVE d where c.LEAD_INVESTIGATOR=d.ID and d.ID= ?";
        return new HashSet<>(jdbcTemplate.query(sql, new Object[]{detective.getId()}, rowMapper));
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        String sql = "select ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION from CRIMINAL_CASE where CASE_NUMBER= ?";
        CriminalCase result = jdbcTemplate.queryForObject(sql, rowMapper, caseNumber);
        return result == null ? Optional.empty() :  Optional.of(result);
    }

    @Override
    public void save(CriminalCase cc) {
        jdbcTemplate.update(
                "insert into CRIMINAL_CASE(ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION, LEAD_INVESTIGATOR ) values(?,?,?,?,?,?,?)",
                cc.getId(), cc.getNumber(), cc.getType(), cc.getStatus(), cc.getShortDescription(), cc.getLeadInvestigator().getId()
        );
    }

    @Override
    public void delete(CriminalCase entity) {
        jdbcTemplate.update("delete from CRIMINAL_CASE where ID =? ", entity.getId());
    }

    @Override
    public int deleteById(Long entityId) {
        return jdbcTemplate.update("delete from CRIMINAL_CASE where ID =? ", entityId);
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
