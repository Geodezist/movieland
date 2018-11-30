package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.bpgdev.movieland.entity.Movie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcMovieDaoTest {

    private List<Movie> expectedMoviesGetAll;
    private List<Movie> expectedMoviesByGenre;

    @Before
    public void before() {
        expectedMoviesByGenre = new ArrayList<>();
        expectedMoviesGetAll = new ArrayList<>();

        Movie movie = new Movie();
        movie.setId(2);
        movie.setNameRussian("Зеленая миля");
        movie.setNameNative("The Green Mile");
        movie.setYearOfRelease(1999);
        movie.setRating(8.9d);
        movie.setPrice(BigDecimal.valueOf(134.67d));
        movie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");
        expectedMoviesGetAll.add(movie);
        expectedMoviesByGenre.add(movie);

        movie = new Movie();
        movie.setId(1);
        movie.setNameRussian("Побег из Шоушенка");
        movie.setNameNative("The Shawshank Redemption");
        movie.setYearOfRelease(1994);
        movie.setRating(8.9d);
        movie.setPrice(BigDecimal.valueOf(123.45d));
        movie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");
        expectedMoviesGetAll.add(movie);
        expectedMoviesByGenre.add(movie);

        movie = new Movie();
        movie.setId(3);
        movie.setNameRussian("Форрест Гамп");
        movie.setNameNative("Forrest Gump");
        movie.setYearOfRelease(1994);
        movie.setRating(8.6d);
        movie.setPrice(BigDecimal.valueOf(200.60d));
        movie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR2,0,140,209_.jpg");
        expectedMoviesGetAll.add(movie);
    }


    @Test
    public void testGetAll() {
        JdbcTemplate mockJdbcTemplateGetAll = mock(JdbcTemplate.class);
        JdbcMovieDao mockMovieDao = new JdbcMovieDao(mockJdbcTemplateGetAll);
        when(mockJdbcTemplateGetAll.query(mockMovieDao.getSqlGetAllMovies(), JdbcMovieDao.MOVIE_ROW_MAPPER)).
                thenReturn(expectedMoviesGetAll);

        assertEquals(3, mockMovieDao.getAll().size());
    }

    @Test
    public void testIGetByGenreId() {
        int genreId = 2;

        JdbcTemplate mockJdbcTemplateGetByGenreId = mock(JdbcTemplate.class);
        JdbcMovieDao mockMovieDao = new JdbcMovieDao(mockJdbcTemplateGetByGenreId);
        when(mockJdbcTemplateGetByGenreId.query(mockMovieDao.getSqlGetMoviesByGenreId(), JdbcMovieDao.MOVIE_ROW_MAPPER, genreId)).
                thenReturn(expectedMoviesByGenre);

        List<Movie> actualMovies = mockMovieDao.getByGenreId(genreId);
        int moviesCount = 2;

        assertEquals(moviesCount, actualMovies.size());
        assertEquals(expectedMoviesByGenre, actualMovies);
    }

    @Test
    public void testGetById() {
        int movieId = 1;

        Movie expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameRussian("Побег из Шоушенка");
        expectedMovie.setNameNative("The Shawshank Redemption");
        expectedMovie.setYearOfRelease(1994);
        expectedMovie.setDescription("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. " +
                "Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, " +
                "царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. " +
                "Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы " +
                "и начинает разрабатывать невероятно дерзкий план своего освобождения.");
        expectedMovie.setRating(8.9d);
        expectedMovie.setPrice(BigDecimal.valueOf(123.45d));
        expectedMovie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");

        JdbcTemplate mockJdbcTemplateGetAll = mock(JdbcTemplate.class);
        JdbcMovieDao mockMovieDao = new JdbcMovieDao(mockJdbcTemplateGetAll);
        when(mockJdbcTemplateGetAll.queryForObject(
                mockMovieDao.getSqlGetMovieById(),
                JdbcMovieDao.MOVIE_DETAIL_ROW_MAPPER, movieId)).thenReturn(expectedMovie);

        Movie actualMovie = mockMovieDao.getById(movieId);

        assertEquals(expectedMovie, actualMovie);
    }
}