package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.common.RequestParameters;
import ua.com.bpgdev.movieland.common.SortingField;
import ua.com.bpgdev.movieland.common.SortingOrder;
import ua.com.bpgdev.movieland.common.SortingParameter;
import ua.com.bpgdev.movieland.entity.Movie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:property/applicationContext-test.xml")
public class JdbcMovieDaoTest {

    private static final Comparator<Movie> MOVIE_RATING_DESCENDING_COMPARATOR =
            Comparator.comparingDouble(Movie::getRating)
                    .reversed()
                    .thenComparingInt(Movie::getId);

    private static final Comparator<Movie> MOVIE_RATING_ASCENDING_COMPARATOR =
            Comparator.comparingDouble(Movie::getRating)
                    .thenComparingInt(Movie::getId);

    private static final Comparator<Movie> MOVIE_PRICE_ASCENDING_COMPARATOR =
            Comparator.comparing(Movie::getPrice)
                    .thenComparingInt(Movie::getId);

    private static final Comparator<Movie> MOVIE_PRICE_DESCENDING_COMPARATOR =
            Comparator.comparing(Movie::getPrice)
                    .reversed()
                    .thenComparingInt(Movie::getId);

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
    public void testIGetAll() {
        JdbcTemplate mockJdbcTemplateGetAll = mock(JdbcTemplate.class);
        JdbcMovieDao mockMovieDao = new JdbcMovieDao(mockJdbcTemplateGetAll);
        when(mockJdbcTemplateGetAll.query(mockMovieDao.getSqlGetAllMovies(), JdbcMovieDao.MOVIE_ROW_MAPPER)).
                thenReturn(expectedMoviesGetAll);

        List<Movie> expectedMovies = mockMovieDao.getAll();
        List<Movie> actualMovies = jdbcMovieDao.getAll();

        assertEquals(3, mockMovieDao.getAll().size());
        assertEquals(24, jdbcMovieDao.getAll().size());

        for (int index = 0; index < expectedMovies.size() - 1; index++) {
            assertEquals(expectedMovies.get(index), actualMovies.get(index));
        }
    }

    @Test
    public void testIGetAllWithRatingOrder() {
        RequestParameters requestParameters = new RequestParameters(
                new SortingParameter(SortingField.RATING, SortingOrder.DESC));
        List<Movie> actualSortedMovies = jdbcMovieDao.getAll(requestParameters);
        List<Movie> expectedMovies = jdbcMovieDao.getAll();

        expectedMovies.sort(MOVIE_RATING_DESCENDING_COMPARATOR);

        assertEquals(expectedMovies, actualSortedMovies);
    }

    @Test
    public void testIGetAllWithPriceOrderAscending() {
        RequestParameters requestParameters = new RequestParameters(
                new SortingParameter(SortingField.PRICE, SortingOrder.ASC));
        List<Movie> actualSortedMovies = jdbcMovieDao.getAll(requestParameters);
        List<Movie> expectedMovies = jdbcMovieDao.getAll();

        expectedMovies.sort(MOVIE_PRICE_ASCENDING_COMPARATOR);

        assertEquals(expectedMovies, actualSortedMovies);
    }

    @Test
    public void testIGetAllWithPriceOrderDescending() {
        RequestParameters requestParameters = new RequestParameters(
                new SortingParameter(SortingField.PRICE, SortingOrder.DESC));
        List<Movie> actualSortedMovies = jdbcMovieDao.getAll(requestParameters);
        List<Movie> expectedMovies = jdbcMovieDao.getAll();

        expectedMovies.sort(MOVIE_PRICE_DESCENDING_COMPARATOR);

        assertEquals(expectedMovies, actualSortedMovies);
    }


    @Test(expected = RuntimeException.class)
    public void testIGetAllWithIllegalRatingOrderValue() {
        RequestParameters requestParameters = new RequestParameters(
                new SortingParameter(SortingField.RATING, SortingOrder.ASC));
        jdbcMovieDao.getAll(requestParameters);
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

    @Test
    public void testIGetThreeRandomWithRatingOrder() {
        RequestParameters requestParameters = new RequestParameters(
                new SortingParameter(SortingField.RATING, SortingOrder.DESC));
        List<Movie> actualSortedMovies = jdbcMovieDao.getThreeRandom(requestParameters);
        List<Movie> expectedMovies = new ArrayList<>(actualSortedMovies);
        expectedMovies.sort(MOVIE_RATING_DESCENDING_COMPARATOR);

        assertEquals(expectedMovies, actualSortedMovies);
    }

    @Test
    public void testIGetThreeRandomWithWrongRatingOrder() {
        RequestParameters requestParameters = new RequestParameters(
                new SortingParameter(SortingField.RATING, SortingOrder.DESC));
        List<Movie> actualSortedMovies = jdbcMovieDao.getThreeRandom(requestParameters);
        List<Movie> expectedMovies = new ArrayList<>(actualSortedMovies);
        expectedMovies.sort(MOVIE_RATING_ASCENDING_COMPARATOR);

        assertNotEquals(expectedMovies, actualSortedMovies);
    }

    @Test
    public void testIGetByGenreId() {
        int genreId = 2;

        JdbcTemplate mockJdbcTemplateGetByGenreId = mock(JdbcTemplate.class);
        JdbcMovieDao mockMovieDao = new JdbcMovieDao(mockJdbcTemplateGetByGenreId);
        when(mockJdbcTemplateGetByGenreId.query(mockMovieDao.getSqlGetMoviesByGenreId(), JdbcMovieDao.MOVIE_ROW_MAPPER, genreId)).
                thenReturn(expectedMoviesByGenre);

        List<Movie> expectedMovies = mockMovieDao.getByGenreId(genreId);
        List<Movie> actualMovies = jdbcMovieDao.getByGenreId(genreId);

        int moviesCount = 7;
        assertEquals(moviesCount, actualMovies.size());
        for (Movie expectedMovie : expectedMovies) {
            actualMovies.remove(expectedMovie);
        }
        assertEquals(moviesCount - expectedMovies.size(), actualMovies.size());
    }

    @Test
    public void testIGetByGenreIdWithRatingOrder() {
        int genreId = 2;

        RequestParameters requestParameters = new RequestParameters(
                new SortingParameter(SortingField.RATING, SortingOrder.DESC));
        List<Movie> actualSortedMovies = jdbcMovieDao.getByGenreId(genreId, requestParameters);
        List<Movie> expectedMovies = jdbcMovieDao.getByGenreId(genreId);

        expectedMovies.sort(MOVIE_RATING_DESCENDING_COMPARATOR);

        assertEquals(expectedMovies, actualSortedMovies);
    }
}
