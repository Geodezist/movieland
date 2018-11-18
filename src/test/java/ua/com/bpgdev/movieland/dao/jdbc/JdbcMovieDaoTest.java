package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.entity.Movie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:property/root-context-test.xml")
public class JdbcMovieDaoTest {

    @Autowired
    private JdbcMovieDao jdbcMovieDao;
    private JdbcMovieDao mockMovieDao;

    @Before
    public void before() {
        List<Movie> expectedMovies = new ArrayList<>();

        Movie movie = new Movie();
        movie.setId(1);
        movie.setNameRussian("Побег из Шоушенка");
        movie.setNameNative("The Shawshank Redemption");
        movie.setYearOfRelease(1994);
        movie.setRating(8.9d);
        movie.setPrice(BigDecimal.valueOf(123.45d));
        movie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");
        expectedMovies.add(movie);

        movie = new Movie();
        movie.setId(2);
        movie.setNameRussian("Зеленая миля");
        movie.setNameNative("The Green Mile");
        movie.setYearOfRelease(1999);
        movie.setRating(8.9d);
        movie.setPrice(BigDecimal.valueOf(134.67d));
        movie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");
        expectedMovies.add(movie);

        movie = new Movie();
        movie.setId(3);
        movie.setNameRussian("Форрест Гамп");
        movie.setNameNative("Forrest Gump");
        movie.setYearOfRelease(1994);
        movie.setRating(8.6d);
        movie.setPrice(BigDecimal.valueOf(200.60d));
        movie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR2,0,140,209_.jpg");
        expectedMovies.add(movie);

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        mockMovieDao = new JdbcMovieDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(mockMovieDao.getSqlGetAllMovies(), JdbcMovieDao.getMovieRowMapper())).
                thenReturn(expectedMovies);
    }

    @Test
    public void testGetAll() {
        assertEquals(3, mockMovieDao.getAll().size());
    }

    @Test
    public void testIGetAll() {
        List<Movie> expectedMovies = mockMovieDao.getAll();
        List<Movie> actualMovies = jdbcMovieDao.getAll();

        assertEquals(3, mockMovieDao.getAll().size());
        assertEquals(24, jdbcMovieDao.getAll().size());

        for (int index = 0; index < expectedMovies.size() - 1; index++) {
            assertEquals(expectedMovies.get(index), actualMovies.get(index));
        }
    }


    @Test
    public void testIGetThreeRandom() {
        List<Movie> actualMoviesFirstTry = jdbcMovieDao.getThreeRandom();
        List<Movie> actualMoviesSecondTry = jdbcMovieDao.getThreeRandom();

        int matchedMovieCount = 0;
        for (int index = 0; index < actualMoviesFirstTry.size() - 1; index++) {
            if (actualMoviesFirstTry.get(index).equals(actualMoviesSecondTry.get(index))) {
                matchedMovieCount++;
            }
        }
        assertNotEquals(3, matchedMovieCount);
    }
}