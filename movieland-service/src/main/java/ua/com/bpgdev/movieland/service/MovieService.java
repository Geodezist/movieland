package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.common.RequestParameters;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    List<Movie> getAll(RequestParameters requestParameters);

    List<Movie> getThreeRandom();
    List<Movie> getThreeRandom(RequestParameters requestParameters);

    List<Movie> getByGenreId(int genreId);
    List<Movie> getByGenreId(int genreId, RequestParameters requestParameters);

    Movie getById(int movieId);
}
