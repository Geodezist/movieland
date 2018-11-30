package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import ua.com.bpgdev.movieland.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDetailRowMapper extends MovieRowMapper {
    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        String description = resultSet.getString("description");
        Movie movie = super.mapRow(resultSet, rowNum);

        if (movie != null) {
            movie.setDescription(description);
        }
        return movie;
    }
}
