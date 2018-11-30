package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.com.bpgdev.movieland.entity.Movie;


public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int id = resultSet.getInt("id");
        String nameRussian = resultSet.getString("title");
        String nameNative = resultSet.getString("title_original");
        int yearOfRelease = resultSet.getInt("release_year");
        double rating = resultSet.getDouble("rating");
        BigDecimal price = resultSet.getBigDecimal("price");
        String picturePath = resultSet.getString("poster_url");


        Movie movie = new Movie();
        movie.setId(id);
        movie.setNameRussian(nameRussian);
        movie.setNameNative(nameNative);
        movie.setYearOfRelease(yearOfRelease);
        movie.setRating(rating);
        movie.setPrice(price);
        movie.setPicturePath(picturePath);

        return movie;
    }
}
