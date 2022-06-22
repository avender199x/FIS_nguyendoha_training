package com.fresher.repository.jdbctemplate.mapper;

import com.fresher.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("ID");
        String username = rs.getString("USERNAME");
        String firstname = rs.getString("FIRSTNAME");
        String lastname = rs.getString("LASTNAME");
        String password = rs.getString("PASSWORD");
        LocalDateTime hiringDate = rs.getTimestamp("HIRINGDATE").toLocalDateTime();

        Person person = new Person();
        person.setId(id);
        person.setUsername(username);
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setPassword(password);
        person.setHiringDate(hiringDate);

        return person;
    }
}
