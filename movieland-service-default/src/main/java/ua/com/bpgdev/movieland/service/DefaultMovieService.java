package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import ua.com.bpgdev.movieland.common.RequestParameters;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.entity.Movie;
import ua.com.bpgdev.movieland.service.enricher.MovieEnricherService;

import java.util.List;

@Service
@Primary
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private MovieEnricherService movieEnricherService;

    public DefaultMovieService(@Autowired MovieDao movieDao,
                               @Autowired MovieEnricherService movieEnricherService
    ) {
        this.movieDao = movieDao;
        this.movieEnricherService = movieEnricherService;
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        return movieDao.getAll(requestParameters);
    }

    @Override
    public List<Movie> getThreeRandom() {
        return movieDao.getThreeRandom();
    }

    @Override
    public List<Movie> getThreeRandom(RequestParameters requestParameters) {
        return movieDao.getThreeRandom(requestParameters);
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return movieDao.getByGenreId(genreId);
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        return movieDao.getByGenreId(genreId, requestParameters);
    }

    @Override
    public Movie getById(int movieId) {
        Movie movie = movieDao.getById(movieId);
        movieEnricherService.enrich(movie);
        return movie;
    }

}
