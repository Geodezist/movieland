package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");

        return new Genre(id, title);
    }
}
