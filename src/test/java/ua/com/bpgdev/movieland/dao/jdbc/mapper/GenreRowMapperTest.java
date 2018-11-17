package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.junit.Before;
import org.junit.Test;
import ua.com.bpgdev.movieland.entity.Genre;
import ua.com.bpgdev.movieland.entity.Movie;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreRowMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("Test Genre");

        Genre expectedGenre = new Genre(1, "Test Genre");

        GenreRowMapper genreRowMapper = new GenreRowMapper();
        Genre actualGenre = genreRowMapper.mapRow(mockResultSet, 1);

        assertEquals(expectedGenre, actualGenre);
    }
}