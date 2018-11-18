package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getThreeRandom();
}
