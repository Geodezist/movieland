package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;

    public DefaultMovieService(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getThreeRandom() {
        return movieDao.getThreeRandom();
    }
}
