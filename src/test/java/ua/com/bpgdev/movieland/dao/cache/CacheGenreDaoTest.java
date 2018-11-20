package ua.com.bpgdev.movieland.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:property/applicationContext-test.xml")
public class CacheGenreDaoTest {
    @Autowired
    private GenreDao cacheGenreDao;

    @Test
    public void testGetAll() {
        List<Genre> genres = cacheGenreDao.getAll();
        assertEquals(15, genres.size());
    }
}