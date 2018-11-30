package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> getByMovieId(int movieId);
}
