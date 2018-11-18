package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:property/applicationContext-test.xml")
public class JdbcGenreDaoTest {

    @Autowired
    private GenreDao jdbcGenreDao;
    private JdbcGenreDao mockGenreDao;

    @Before
    public void before(){
        List<Genre> expectedGenre = new ArrayList<>();

        Genre genre = new Genre(1, "драма");
        expectedGenre.add(genre);
        genre = new Genre(2, "криминал");
        expectedGenre.add(genre);
        genre = new Genre(3, "фэнтези");
        expectedGenre.add(genre);

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        //when(mockJdbcTemplate.query()).thenReturn(expectedGenre);
    }


    @Test
    public void testIGetAll() {
        List<Genre> actualGenres = jdbcGenreDao.getAll();
        assertEquals(15, actualGenres.size());
    }
}