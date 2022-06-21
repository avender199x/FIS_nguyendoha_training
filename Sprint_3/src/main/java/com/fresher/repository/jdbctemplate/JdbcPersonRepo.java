package com.fresher.repository.jdbctemplate;

import com.fresher.model.Person;
import com.fresher.repository.PersonRepo;
import com.fresher.repository.jdbctemplate.mapper.PersonRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
//import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcPersonRepo extends JdbcAbstractRepo<Person>
        implements PersonRepo {
    protected RowMapper<Person> rowMapper = new PersonRowMapper();

    private static final String[] SPECIAL_CHARS = new String[]{"$", "#", "&", "%"};

    public JdbcPersonRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<Person> findById(Long id) {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON where ID= ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON where USERNAME= ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, username));
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON where FIRSTNAME= ? and LASTNAME= ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, new Object[]{firstName, lastName}, rowMapper));
    }

    @Override
    public int updatePassword(Long personId, String newPass) {
        String sql = "update PERSON set password=? where ID = ?";
        return jdbcTemplate.update(sql, newPass, personId);
    }

    @Override
    public Person update(Person person) {
//        if (StringUtils.indexOfAny(person.getFirstName(), SPECIAL_CHARS) != -1 ||
//                StringUtils.indexOfAny(person.getLastName(), SPECIAL_CHARS) != -1) {
//            throw new IllegalArgumentException("Text contains weird characters!");
//        }
        jdbcTemplate.update("update PERSON set USERNAME=?, FIRSTNAME=?, LASTNAME=?, PASSWORD=?, MODIFIED_AT=? where ID=?",
                 person.getUsername(), person.getFirstName(), person.getLastName(), person.getPassword(), LocalDateTime.now(), person.getId()
        );
        return person;
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update(
                "insert into PERSON(ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE, MODIFIED_AT, CREATED_AT, VERSION) values(?,?,?,?,?,?,?,?,?)",
                person.getId(), person.getUsername(), person.getFirstName(), person.getLastName(), person.getPassword(),
                person.getHiringDate(), LocalDateTime.now(), LocalDateTime.now(), 1
        );
    }

    @Override
    public Set<Person> findAll() {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper));
    }

    @Override
    public void delete(Person entity) {
        jdbcTemplate.update("delete from PERSON where ID =? ", entity.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from PERSON where ID =? ", id);
    }

    @Override
    public long count() {
        String sql = "select count(*) from PERSON";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}