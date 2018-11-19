package ua.com.bpgdev.movieland.dao;

import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
}
