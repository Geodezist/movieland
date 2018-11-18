package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:property/root-context-test.xml")
public class JdbcGenreDaoTest {

    @Autowired
    private GenreDao jdbcGenreDao;

    @Test
    public void testIGetAll() {
        List<Genre> expectedGenres = new ArrayList<>();

        Genre expectedGenre = new Genre(1, "драма");
        expectedGenres.add(expectedGenre);
        expectedGenre = new Genre(2, "криминал");
        expectedGenres.add(expectedGenre);
        expectedGenre = new Genre(3, "фэнтези");
        expectedGenres.add(expectedGenre);

        List<Genre> actualGenres = jdbcGenreDao.getAll();
        assertEquals(15, actualGenres.size());
        for (Genre genre : expectedGenres) {
            actualGenres.remove(genre);
        }
        assertEquals(12, actualGenres.size());
    }
}