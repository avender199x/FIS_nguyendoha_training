package com.fresher.repository.jdbctemplate.mapper;

import com.fresher.model.Storage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageRowMapper implements RowMapper<Storage> {
    @Override
    public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("ID");
        String name = rs.getString("NAME");
        String location = rs.getString("LOCATION");

        Storage storage = new Storage();
        storage.setId(id);
        storage.setName(name);
        storage.setLocation(location);

        return storage;
    }
}
