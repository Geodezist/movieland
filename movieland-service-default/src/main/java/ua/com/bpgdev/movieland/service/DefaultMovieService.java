package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import ua.com.bpgdev.movieland.common.CurrencyCode;
import ua.com.bpgdev.movieland.common.RequestParameters;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.entity.CurrencyRate;
import ua.com.bpgdev.movieland.entity.Movie;
import ua.com.bpgdev.movieland.service.enricher.MovieEnricherService;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Primary
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private MovieEnricherService movieEnricherService;
    private CurrencyRateService currencyRateService;

    public DefaultMovieService(@Autowired MovieDao movieDao,
                               @Autowired MovieEnricherService movieEnricherService,
                               @Autowired CurrencyRateService currencyRateService
    ) {
        this.movieDao = movieDao;
        this.movieEnricherService = movieEnricherService;
        this.currencyRateService = currencyRateService;
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        List<Movie> movies = movieDao.getAll(requestParameters);
        applyCurrencyRate(movies, requestParameters);
        return movies;
    }

    @Override
    public List<Movie> getThreeRandom() {
        return movieDao.getThreeRandom();
    }

    @Override
    public List<Movie> getThreeRandom(RequestParameters requestParameters) {
        List<Movie> movies = movieDao.getThreeRandom(requestParameters);
        applyCurrencyRate(movies, requestParameters);
        return movies;
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return movieDao.getByGenreId(genreId);
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        List<Movie> movies = movieDao.getByGenreId(genreId, requestParameters);
        applyCurrencyRate(movies, requestParameters);
        return movies;
    }

    @Override
    public Movie getById(int movieId, RequestParameters requestParameters) {
        Movie movie = movieDao.getById(movieId);
        movieEnricherService.enrich(movie);
        applyCurrencyRate(new ArrayList<>(Collections.singletonList(movie)), requestParameters);
        return movie;
    }

    public void applyCurrencyRate(List<Movie> movies, RequestParameters requestParameters) {
        CurrencyCode currencyCode = requestParameters.getCurrencyParameter();
        if (currencyCode != null) {
            CurrencyRate currencyRate = currencyRateService.getRateByCode(currencyCode.getCurrencyCode());
            for (Movie movie : movies) {
                movie.setPrice(movie.getPrice().divide(currencyRate.getRate(), 2, RoundingMode.HALF_EVEN));
            }
        }
    }


}
