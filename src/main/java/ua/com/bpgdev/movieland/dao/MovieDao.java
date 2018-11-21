package ua.com.bpgdev.movieland.dao;

import ua.com.bpgdev.movieland.common.ParameterInfo;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll();
    List<Movie> getAll(ParameterInfo parameterInfo);
    List<Movie> getThreeRandom();
    List<Movie> getThreeRandom(ParameterInfo parameterInfo);
    List<Movie> getByGenreId(int genreId);
    List<Movie> getByGenreId(int genreId, ParameterInfo parameterInfo);


}
