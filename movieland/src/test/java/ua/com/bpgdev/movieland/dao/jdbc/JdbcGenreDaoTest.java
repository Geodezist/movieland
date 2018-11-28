package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:property/applicationContext-test.xml")
public class JdbcGenreDaoTest {

    @Autowired
    private GenreDao jdbcGenreDao;
    private List<Genre> expectedGenres = new ArrayList<>();


    @Before
    public void before() {
        Genre expectedGenre = new Genre(6, "биография");
        expectedGenres.add(expectedGenre);
        expectedGenre = new Genre(1, "драма");
        expectedGenres.add(expectedGenre);
        expectedGenre = new Genre(7, "комедия");
        expectedGenres.add(expectedGenre);
    }

    @Test
    public void testIGetAll() {
        List<Genre> actualGenres = jdbcGenreDao.getAll();
        assertEquals(15, actualGenres.size());
        for (Genre genre : expectedGenres) {
            actualGenres.remove(genre);
        }
        assertEquals(12, actualGenres.size());
    }

    @Test
    public void testGetByMovieId() {
        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcGenreDao mockJdbcGenreDao = new JdbcGenreDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(any(), eq(JdbcGenreDao.GENRE_ROW_MAPPER), eq(5))).thenReturn(expectedGenres);

        List<Genre> actualGenres = mockJdbcGenreDao.getByMovieId(5);
        assertEquals(expectedGenres, actualGenres);
    }

    @Test
    public void testIGetByMovieId() {
        List<Genre> actualGenres = jdbcGenreDao.getByMovieId(5);
        assertEquals(expectedGenres, actualGenres);
    }
}