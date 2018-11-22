package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;

    public DefaultMovieService(@Qualifier("jdbcMovieDao") MovieDao movieDao){
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

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return movieDao.getByGenreId(genreId);
    }
}
