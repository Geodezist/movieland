package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

@Service
@Primary
public class DefaultGenreService implements GenreService {
    private GenreDao genreDao;

    public DefaultGenreService(@Qualifier("cacheGenreDao") GenreDao genreDao){
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public List<Genre> getByMovieId(int movieId){
        return genreDao.getByMovieId(movieId);
    }
}
