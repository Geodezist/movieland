package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    List<Genre> getByMovieId(int movieId);
}
