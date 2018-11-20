package ua.com.bpgdev.movieland.dao;

import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll();
    List<Movie> getThreeRandom();
    List<Movie> getByGenreId(int genreId);

}
