package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.entity.Movie;


public class MovieDetailRowMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MovieRowMapper movieRowMapper = new MovieRowMapper();
        Movie movie = movieRowMapper.mapRow(resultSet, rowNum);
        String description = resultSet.getString("description");

        movie.setDescription(description);

        return movie;
    }
}
