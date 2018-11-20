package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.MovieRowMapper;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

public class JdbcMovieDao implements MovieDao {
    public static final RowMapper<Movie> MOVIE_ROW_MAPPER = new MovieRowMapper();
    @Value("${sql.sql_get_all_movies}")
    private String sqlGetAllMovies;
    @Value("${sql.sql_get_random_movies}")
    private String sqlGetRandomMovies;
    @Value(("${sql.sql_get_movies_by_genre_id}"))
    private String sqlGetMoviesByGenreId;

    private JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(sqlGetAllMovies, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom() {
        return jdbcTemplate.query(sqlGetRandomMovies, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return jdbcTemplate.query(sqlGetMoviesByGenreId, MOVIE_ROW_MAPPER, genreId);
    }

    public String getSqlGetAllMovies() {
        return sqlGetAllMovies;
    }

    public String getSqlGetMoviesByGenreId() {
        return sqlGetMoviesByGenreId;
    }
}
