package ua.com.bpgdev.movieland.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.datasource.MovieLandDataSource;
import ua.com.bpgdev.movieland.dao.datasource.PostgresMovieLandDataSource;
import ua.com.bpgdev.movieland.dao.jdbc.JdbcMovieDao;
import ua.com.bpgdev.movieland.entity.Movie;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultMovieServiceTest {
    private MovieDao mockMovieDao;
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

        movie = new Movie();
        movie.setId(4);
        movie.setNameRussian("Список Шиндлера");
        movie.setNameNative("Schindler's List");
        movie.setYearOfRelease(1993);
        movie.setRating(8.7d);
        movie.setPrice(BigDecimal.valueOf(150.50d));
        movie.setPicturePath("");
        expectedMovies.add(movie);

        mockMovieDao = mock(JdbcMovieDao.class);
        when(mockMovieDao.getAll()).thenReturn(expectedMovies);

    }

    @Test
    public void getAll() {
        MovieService movieService = new DefaultMovieService(mockMovieDao);
        List<Movie> actualMovies = movieService.getAll();

        assertEquals(expectedMovies.size(), actualMovies.size());
        for (int index = 0; index < actualMovies.size(); index++) {
            assertEquals(index + 1, actualMovies.get(index).getId());
        }
    }

    @Test
    public void getThreeRandom() throws IOException {
        PostgresMovieLandDataSource postgresMovieLandDataSource = new PostgresMovieLandDataSource();
        MovieDao movieDao = new JdbcMovieDao(postgresMovieLandDataSource);
        ((JdbcMovieDao) movieDao).init();
        MovieService movieService = new DefaultMovieService(movieDao);

        List<Movie> actualMoviesFirstTry = movieService.getThreeRandom();
        List<Movie> actualMoviesSecondTry = movieService.getThreeRandom();

        assertNotEquals(actualMoviesFirstTry, actualMoviesSecondTry);

    }
}