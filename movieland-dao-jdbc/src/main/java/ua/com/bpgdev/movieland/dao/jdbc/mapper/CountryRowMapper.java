package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.com.bpgdev.movieland.entity.Country;

public class CountryRowMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("title");
        return new Country(id, name);
    }
}
