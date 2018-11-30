package ua.com.bpgdev.movieland.dao;

import ua.com.bpgdev.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getAll();
    List<Country> getByMovieId(int movieId);
}
