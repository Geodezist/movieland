package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.common.RequestParameters;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.MovieRowMapper;
import ua.com.bpgdev.movieland.dao.jdbc.querybuilder.MovieQueryBuilder;
import ua.com.bpgdev.movieland.dao.jdbc.querybuilder.QueryBuilder;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    public static final RowMapper<Movie> MOVIE_ROW_MAPPER = new MovieRowMapper();
    @Value("${sql.sql_get_all_movies}")
    private String sqlGetAllMovies;
    @Value("${sql.sql_get_random_movies}")
    private String sqlGetRandomMovies;
    @Value(("${sql.sql_get_movies_by_genre_id}"))
    private String sqlGetMoviesByGenreId;

    private JdbcTemplate jdbcTemplate;
    private QueryBuilder queryBuilder = new MovieQueryBuilder();

    public JdbcMovieDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(sqlGetAllMovies, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        if (requestParameters == null) {
            return getAll();
        }
        String query = queryBuilder.build(sqlGetAllMovies, requestParameters);
        return jdbcTemplate.query(query, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom() {
        return jdbcTemplate.query(sqlGetRandomMovies, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom(RequestParameters requestParameters) {
        if (requestParameters == null) {
            return getThreeRandom();
        }
        String query = queryBuilder.build(sqlGetRandomMovies, requestParameters);
        return jdbcTemplate.query(query, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return jdbcTemplate.query(sqlGetMoviesByGenreId, MOVIE_ROW_MAPPER, genreId);
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        if (requestParameters == null) {
            return getByGenreId(genreId);
        }
        String query = queryBuilder.build(sqlGetMoviesByGenreId, requestParameters);
        return jdbcTemplate.query(query, MOVIE_ROW_MAPPER, genreId);
    }

    String getSqlGetAllMovies() {
        return sqlGetAllMovies;
    }

    String getSqlGetMoviesByGenreId() {
        return sqlGetMoviesByGenreId;
    }
}