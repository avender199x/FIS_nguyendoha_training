package com.fresher.repository.jdbctemplate;

import com.fresher.core.Rank;
import com.fresher.model.Detective;
import com.fresher.repository.DetectiveRepo;
import com.fresher.repository.jdbctemplate.mapper.DetectiveRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Transactional
@Repository
public class JdbcDetectiveRepo extends JdbcAbstractRepo<Detective>
        implements DetectiveRepo {

    private RowMapper<Detective> rowMapper = new DetectiveRowMapper();

    public JdbcDetectiveRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<Detective> findById(Long id) {
        String sql = "select d.ID, d.BADGE_NUMBER, d.RANK, d.ARMED, d.STATUS,d.PERSON_ID, " +
                "p.USERNAME, p.FIRSTNAME, p.LASTNAME, p.HIRINGDATE "+
                "from DETECTIVE d, PERSON p where d.ID= ? and d.PERSON_ID=p.ID";
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        String sql = "select ID, BADGE_NUMBER, RANK, ARMED, STATUS,PERSON_ID from DETECTIVE where BADGE_NUMBER= ?";
        Detective detective = jdbcTemplate.queryForObject(sql, rowMapper, badgeNumber);
        return detective == null ? Optional.empty() : Optional.of(detective);
    }

    @Override
    public void save(Detective detective) {
        jdbcTemplate.update(
                "insert into DETECTIVE(ID, BADGE_NUMBER, RANK, ARMED, STATUS,PERSON_ID) values(?,?,?,?,?,?)",
                detective.getId(), detective.getBadgeNumber(), detective.getRank(),
                                    detective.getStatus(), detective.getPerson().getId()
        );
    }

    @Override
    public Set<Detective> findAll() {
        String sql = "select ID, BADGE_NUMBER, RANK, ARMED, STATUS,PERSON_ID from DETECTIVE";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper));
    }

    @Override
    public Set<Detective> findbyRank(Rank rank) {
        String sql = "select ID, BADGE_NUMBER, RANK, ARMED, STATUS,PERSON_ID from DETECTIVE where RANK= ?";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper, rank));
    }

    @Override
    public void delete(Detective entity) {
        jdbcTemplate.update("delete from DETECTIVE where ID =? ", entity.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from DETECTIVE where ID =? ", id);
    }
}
