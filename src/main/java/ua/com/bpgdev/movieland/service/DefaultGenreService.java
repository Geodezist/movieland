package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

public class DefaultGenreService implements GenreService {
    private GenreDao genreDao;

    public DefaultGenreService(GenreDao genreDao){
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
