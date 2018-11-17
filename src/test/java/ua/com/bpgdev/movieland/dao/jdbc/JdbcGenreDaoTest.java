package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.dao.datasource.MovieLandDataSource;
import ua.com.bpgdev.movieland.entity.Genre;
import ua.com.bpgdev.movieland.testutil.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@TestPropertySource("classpath:property/sqls.properties")
public class JdbcGenreDaoTest {

    @Value("${sql.sql_get_all_genres}")
    private String  sqlGetAllGenres;
    private GenreDao genreDao;
    private JdbcGenreDao mockGenreDao;

    @Before
    public void before() throws IOException {
        List<Genre> expectedGenre = new ArrayList<>();

        Genre genre = new Genre(1, "драма");
        expectedGenre.add(genre);
        genre = new Genre(2, "криминал");
        expectedGenre.add(genre);
        genre = new Genre(3, "фэнтези");
        expectedGenre.add(genre);

        String dataSourceConfigFile = "/property/application.properties";
        MovieLandDataSource movieLandDataSource = new MovieLandDataSource(dataSourceConfigFile);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(movieLandDataSource.getDataSource());

        genreDao = new JdbcGenreDao(jdbcTemplate);

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        //when(mockJdbcTemplate.query()).thenReturn(expectedGenre);
    }


    @Test
    public void testIGetAll() {
        ReflectionTestUtils.setField(genreDao, "sqlGetAllGenres", sqlGetAllGenres);
        List<Genre> actualGenres = genreDao.getAll();
        assertEquals(15, actualGenres.size());
    }
}