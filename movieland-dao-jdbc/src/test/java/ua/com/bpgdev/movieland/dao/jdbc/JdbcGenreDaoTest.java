package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcGenreDaoTest {

    private List<Genre> expectedGenres;

    @Before
    public void before() {
        expectedGenres = new ArrayList<>();

        expectedGenres = new ArrayList<>();
        Genre expectedGenre = new Genre(6, "биография");
        expectedGenres.add(expectedGenre);
        expectedGenre = new Genre(1, "драма");
        expectedGenres.add(expectedGenre);
        expectedGenre = new Genre(7, "комедия");
        expectedGenres.add(expectedGenre);
    }

    @Test
    public void getAll() {
        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcGenreDao mockJdbcGenreDao = new JdbcGenreDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(
                eq(mockJdbcGenreDao.getSqlGetAllGenres()),
                eq(JdbcGenreDao.GENRE_ROW_MAPPER))).thenReturn(expectedGenres);

        List<Genre> actualGenres = mockJdbcGenreDao.getAll();
        assertEquals(expectedGenres, actualGenres);
    }

    @Test
    public void testGetByMovieId() {
        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcGenreDao mockJdbcGenreDao = new JdbcGenreDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(
                eq(mockJdbcGenreDao.getSqlGetGenresByMovieId()),
                eq(JdbcGenreDao.GENRE_ROW_MAPPER),
                eq(5))).thenReturn(expectedGenres);

        List<Genre> actualGenres = mockJdbcGenreDao.getByMovieId(5);
        assertEquals(expectedGenres, actualGenres);
    }
}