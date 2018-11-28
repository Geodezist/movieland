package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.GenreRowMapper;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

@Repository
@Primary
public class JdbcGenreDao implements GenreDao {
    static final RowMapper<Genre> GENRE_ROW_MAPPER = new GenreRowMapper();
    @Value("${sql.genres.getAll}")
    private String sqlGetAllGenres;
    @Value("${sql.genres.getByMovieId}")
    private String sqlGetGenresByMovieId;
    private JdbcTemplate jdbcTemplate;


    public JdbcGenreDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(sqlGetAllGenres, GENRE_ROW_MAPPER);
    }

    @Override
    public List<Genre> getByMovieId(int movieId) {
        return jdbcTemplate.query(sqlGetGenresByMovieId, GENRE_ROW_MAPPER, movieId);
    }
}
