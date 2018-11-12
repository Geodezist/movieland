package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.datasource.MovieLandDataSource;
import ua.com.bpgdev.movieland.dao.datasource.PostgresMovieLandDataSource;
import ua.com.bpgdev.movieland.entity.Movie;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcMovieDaoTest {

    private MovieDao movieDao;
    private MovieDao mockMovieDao;
    private JdbcTemplate mockJdbcTemplate;
    private List<Movie> expectedMovies;

    @Before
    public void before() {

        expectedMovies = new ArrayList<>();
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

        mockJdbcTemplate = mock(JdbcTemplate.class);
        when(mockJdbcTemplate.query(JdbcMovieDao.getSqlGetAllMovies(), JdbcMovieDao.getMovieRowMapper())).
                thenReturn(expectedMovies);

        String dataSourceConfigFile = "/src/main/resources/property/datasource-property.yml";
        MovieLandDataSource movieLandDataSource = new PostgresMovieLandDataSource(
                dataSourceConfigFile);

        try {
            mockMovieDao = new JdbcMovieDao(movieLandDataSource);
            ((JdbcMovieDao) mockMovieDao).setJdbcTemplate(mockJdbcTemplate);

            movieDao = new JdbcMovieDao(movieLandDataSource);
            ((JdbcMovieDao) movieDao).init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAll() {
        List<Movie> actualMovies = movieDao.getAll();

        assertEquals(24, movieDao.getAll().size());

        for (int index = 0; index < expectedMovies.size()-1; index++) {
            assertEquals(expectedMovies.get(index), actualMovies.get(index));
        }
    }
}