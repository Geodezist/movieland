package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.junit.Before;
import org.junit.Test;
import ua.com.bpgdev.movieland.entity.Movie;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRowMapperTest {
    private ResultSet mockResultSet;
    private Movie expectedMovie;

    @Before
    public void before() throws SQLException {
        mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("Test nameRussian");
        when(mockResultSet.getString("title_original")).thenReturn("Test nameNative");
        when(mockResultSet.getInt("release_year")).thenReturn(2018);
        when(mockResultSet.getDouble("rating")).thenReturn(10.0d);
        when(mockResultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(1000.00d));
        when(mockResultSet.getString("poster_url")).thenReturn("Test picturePath");

        expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameRussian("Test nameRussian");
        expectedMovie.setNameNative("Test nameNative");
        expectedMovie.setYearOfRelease(2018);
        expectedMovie.setRating(10.0d);
        expectedMovie.setPrice(BigDecimal.valueOf(1000.00d));
        expectedMovie.setPicturePath("Test picturePath");

    }

    @Test
    public void testMapRow() throws SQLException {
        MovieRowMapper movieRowMapper = new MovieRowMapper();
        Movie actialMovie = movieRowMapper.mapRow(mockResultSet, 1);
        assertEquals(expectedMovie, actialMovie);

    }
}