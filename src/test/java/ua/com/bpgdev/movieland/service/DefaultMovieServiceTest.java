package ua.com.bpgdev.movieland.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.jdbc.JdbcMovieDao;
import ua.com.bpgdev.movieland.entity.Movie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:property/applicationContext-test.xml")
public class DefaultMovieServiceTest {
    @Autowired
    private JdbcMovieDao jdbcMovieDao;

    private List<Movie> expectedMoviesGetAll;
    private List<Movie> expectedMoviesByGenre;

    @Before
    public void before() {
        expectedMoviesGetAll = new ArrayList<>();
        expectedMoviesByGenre = new ArrayList<>();

        Movie movie = new Movie();
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
        movie.setId(3);
        movie.setNameRussian("Форрест Гамп");
        movie.setNameNative("Forrest Gump");
        movie.setYearOfRelease(1994);
        movie.setRating(8.6d);
        movie.setPrice(BigDecimal.valueOf(200.60d));
        movie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR2,0,140,209_.jpg");
        expectedMoviesGetAll.add(movie);

        movie = new Movie();
        movie.setId(4);
        movie.setNameRussian("Список Шиндлера");
        movie.setNameNative("Schindler's List");
        movie.setYearOfRelease(1993);
        movie.setRating(8.7d);
        movie.setPrice(BigDecimal.valueOf(150.50d));
        movie.setPicturePath("");
        expectedMoviesGetAll.add(movie);
    }

    @Test
    public void testGetAll() {
        MovieDao mockMovieDaoGetAll = mock(JdbcMovieDao.class);
        when(mockMovieDaoGetAll.getAll()).thenReturn(expectedMoviesGetAll);
        MovieService movieServiceGetAll = new DefaultMovieService(mockMovieDaoGetAll);
        List<Movie> actualMovies = movieServiceGetAll.getAll();

        assertEquals(expectedMoviesGetAll.size(), actualMovies.size());
        for (int index = 0; index < actualMovies.size(); index++) {
            assertEquals(index + 1, actualMovies.get(index).getId());
        }
    }

    @Test
    public void testIGetThreeRandom() {
        MovieService movieService = new DefaultMovieService(jdbcMovieDao);

        List<Movie> actualMoviesFirstTry = movieService.getThreeRandom();
        List<Movie> actualMoviesSecondTry = movieService.getThreeRandom();

        assertEquals(3,actualMoviesFirstTry.size());
        assertEquals(3,actualMoviesSecondTry.size());
        assertNotEquals(actualMoviesFirstTry, actualMoviesSecondTry);
    }

    @Test
    public void testGetByGenreId(){
        int genreId = 2;
        MovieDao mockMovieDaoGetByGenreId = mock(JdbcMovieDao.class);
        when(mockMovieDaoGetByGenreId.getByGenreId(genreId)).thenReturn(expectedMoviesByGenre);
        MovieService movieServiceGetByGenreId = new DefaultMovieService(mockMovieDaoGetByGenreId);
        List<Movie> actualMoviesByGenre = movieServiceGetByGenreId.getByGenreId(genreId);

        assertEquals(expectedMoviesByGenre, actualMoviesByGenre);
    }

    @Test
    public void testIGetByGenreId(){
        int genreId = 2;
        MovieService movieService = new DefaultMovieService(jdbcMovieDao);
        List<Movie> actualMoviesByGenre = movieService.getByGenreId(genreId);

        int moviesCount = 7;
        assertEquals(moviesCount,actualMoviesByGenre.size());
        for (Movie expectedMovie : expectedMoviesByGenre) {
            actualMoviesByGenre.remove(expectedMovie);
        }
        assertEquals(moviesCount - expectedMoviesByGenre.size(), actualMoviesByGenre.size());
    }

}