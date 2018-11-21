package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.common.ParameterInfo;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;

    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getAll(ParameterInfo parameterInfo) {
        return movieDao.getAll(parameterInfo);
    }

    @Override
    public List<Movie> getThreeRandom() {
        return movieDao.getThreeRandom();
    }

    @Override
    public List<Movie> getThreeRandom(ParameterInfo parameterInfo) {
        return movieDao.getThreeRandom(parameterInfo);
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return movieDao.getByGenreId(genreId);
    }

    @Override
    public List<Movie> getByGenreId(int genreId, ParameterInfo parameterInfo) {
        return movieDao.getByGenreId(genreId, parameterInfo);
    }
}
