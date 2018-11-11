package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.datasource.MovieLandDataSource;
import ua.com.bpgdev.movieland.dao.datasource.PostgresMovieLandDataSource;

import java.io.IOException;

import static org.junit.Assert.*;

public class JdbcMovieDaoTest {

    private MovieLandDataSource movieLandDataSource;
    private MovieDao movieDao;

    @Before
    public void before(){
        movieLandDataSource = new PostgresMovieLandDataSource();
        try {
            movieDao = new JdbcMovieDao(movieLandDataSource.getDataSource());
            ((JdbcMovieDao) movieDao).init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAll() {
        assertEquals(24, movieDao.getAll().size());
    }
}