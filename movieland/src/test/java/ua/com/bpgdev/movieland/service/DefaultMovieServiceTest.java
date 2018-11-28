package ua.com.bpgdev.movieland.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.jdbc.JdbcMovieDao;
import ua.com.bpgdev.movieland.entity.*;
import ua.com.bpgdev.movieland.service.enricher.DefaultMovieEnricherService;

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
    @Autowired
    private DefaultMovieEnricherService defaultMovieEnricherService;


    private List<Movie> expectedMoviesGetAll;
    private List<Movie> expectedMoviesByGenre;
    private Movie expectedMovieForGetMovieById;
    private List<Country> expectedCountriesForGetMovieById;
    private List<Genre> expectedGenresForGetMovieById;
    private List<Review> expectedReviewsForGetMovieById;


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

        expectedGenresForGetMovieById = new ArrayList<>();
        expectedGenresForGetMovieById.add(new Genre(15, "вестерн"));

        expectedCountriesForGetMovieById = new ArrayList<>();
        expectedCountriesForGetMovieById.add(new Country(45, "Германия"));
        expectedCountriesForGetMovieById.add(new Country(67, "Испания"));
        expectedCountriesForGetMovieById.add(new Country(68, "Италия"));
        expectedCountriesForGetMovieById.add(new Country(157, "США"));

        User expextedUserForGetMovieById = new User(4, "Дэрил Брайант");

        expectedReviewsForGetMovieById = new ArrayList<>();
        expectedReviewsForGetMovieById.add(new Review(25, expextedUserForGetMovieById,
                "Для воскресного вечернего просмотра подходит по всем критериям."));

        expectedMovieForGetMovieById = new Movie();
        expectedMovieForGetMovieById.setId(21);
        expectedMovieForGetMovieById.setNameRussian("Хороший, плохой, злой");
        expectedMovieForGetMovieById.setNameNative("Il buono, il brutto, il cattivo");
        expectedMovieForGetMovieById.setYearOfRelease(1979);
        expectedMovieForGetMovieById.setRating(8.5d);
        expectedMovieForGetMovieById.setDescription("В разгар гражданской войны таинственный стрелок скитается по просторам Дикого Запада." +
                " У него нет ни дома, ни друзей, ни компаньонов, пока он не встречает двоих незнакомцев," +
                " таких же безжалостных и циничных. По воле судьбы трое мужчин вынуждены объединить свои усилия в" +
                " поисках украденного золота. Но совместная работа — не самое подходящее занятие для таких отъявленных" +
                " бандитов, как они. Компаньоны вскоре понимают, что в их дерзком и опасном путешествии по разоренной" +
                " войной стране самое важное — никому не доверять и держать пистолет наготове, если хочешь остаться в живых.");
        expectedMovieForGetMovieById.setPrice(BigDecimal.valueOf(13000, 2));
        expectedMovieForGetMovieById.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BOTQ5NDI3MTI4MF5BMl5BanBnXkFtZTgwNDQ4ODE5MDE@._V1._SX140_CR0,0,140,209_.jpg");
        expectedMovieForGetMovieById.setCountries(expectedCountriesForGetMovieById);
        expectedMovieForGetMovieById.setGenres(expectedGenresForGetMovieById);
        expectedMovieForGetMovieById.setReviews(expectedReviewsForGetMovieById);
    }

    @Test
    public void testGetAll() {
        MovieDao mockMovieDaoGetAll = mock(JdbcMovieDao.class);
        when(mockMovieDaoGetAll.getAll()).thenReturn(expectedMoviesGetAll);
        MovieService movieServiceGetAll = new DefaultMovieService(mockMovieDaoGetAll, defaultMovieEnricherService);
        List<Movie> actualMovies = movieServiceGetAll.getAll();

        assertEquals(expectedMoviesGetAll.size(), actualMovies.size());
        for (int index = 0; index < actualMovies.size(); index++) {
            assertEquals(index + 1, actualMovies.get(index).getId());
        }
    }

    @Test
    public void testIGetThreeRandom() {
        MovieService movieService = new DefaultMovieService(jdbcMovieDao, defaultMovieEnricherService);

        List<Movie> actualMoviesFirstTry = movieService.getThreeRandom();
        List<Movie> actualMoviesSecondTry = movieService.getThreeRandom();

        assertEquals(3, actualMoviesFirstTry.size());
        assertEquals(3, actualMoviesSecondTry.size());
        assertNotEquals(actualMoviesFirstTry, actualMoviesSecondTry);
    }

    @Test
    public void testGetByGenreId() {
        int genreId = 2;
        MovieDao mockMovieDaoGetByGenreId = mock(JdbcMovieDao.class);
        when(mockMovieDaoGetByGenreId.getByGenreId(genreId)).thenReturn(expectedMoviesByGenre);
        MovieService movieServiceGetByGenreId = new DefaultMovieService(mockMovieDaoGetByGenreId, defaultMovieEnricherService);
        List<Movie> actualMoviesByGenre = movieServiceGetByGenreId.getByGenreId(genreId);

        assertEquals(expectedMoviesByGenre, actualMoviesByGenre);
    }

    @Test
    public void testIGetByGenreId() {
        int genreId = 2;
        MovieService movieService = new DefaultMovieService(jdbcMovieDao, defaultMovieEnricherService);
        List<Movie> actualMoviesByGenre = movieService.getByGenreId(genreId);

        int moviesCount = 7;
        assertEquals(moviesCount, actualMoviesByGenre.size());
        for (Movie expectedMovie : expectedMoviesByGenre) {
            actualMoviesByGenre.remove(expectedMovie);
        }
        assertEquals(moviesCount - expectedMoviesByGenre.size(), actualMoviesByGenre.size());
    }


    @Test
    public void testGetById() {
        int movieId = 21;

        MovieDao mockMovieDaoGetById = mock(JdbcMovieDao.class);
        when(mockMovieDaoGetById.getById(movieId)).thenReturn(expectedMovieForGetMovieById);
        MovieService movieServiceGetById = new DefaultMovieService(mockMovieDaoGetById, defaultMovieEnricherService);

        Movie actualMovie = movieServiceGetById.getById(movieId);
        assertEquals(expectedMovieForGetMovieById, actualMovie);
    }

    @Test
    public void testIGetById() {
        MovieService movieService = new DefaultMovieService(jdbcMovieDao, defaultMovieEnricherService);
        Movie actualMovie = movieService.getById(21);

        assertEquals(expectedMovieForGetMovieById, actualMovie);
        assertEquals(expectedGenresForGetMovieById, actualMovie.getGenres());
        assertEquals(expectedCountriesForGetMovieById, actualMovie.getCountries());
    }

}