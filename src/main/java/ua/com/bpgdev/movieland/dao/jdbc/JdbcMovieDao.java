package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.datasource.MovieLandDataSource;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.MovieRowMapper;
import ua.com.bpgdev.movieland.entity.Movie;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

public class JdbcMovieDao implements MovieDao {
    private static final RowMapper<Movie> MOVIE_ROW_MAPPER = new MovieRowMapper();
    private static final String SQL_GET_ALL_MOVIES = "SELECT\n" +
            "       moie.id,\n" +
            "       moie.title,\n" +
            "       moie.title_original,\n" +
            "       moie.release_year,\n" +
            "       moie.rating,\n" +
            "       moie.price,\n" +
            "       mepr.poster_url\n" +
            "FROM movie moie\n" +
            "       JOIN movie_poster mepr\n" +
            "         ON moie.id = mepr.movie_id;";
    private static final String SQL_GET_THREE_RANDOM_MOVIES = "SELECT\n" +
            "       vmtm.id,\n" +
            "       vmtm.title,\n" +
            "       vmtm.title_original,\n" +
            "       vmtm.release_year,\n" +
            "       vmtm.rating,\n" +
            "       vmtm.price,\n" +
            "       vmtm.poster_url\n" +
            "FROM v_movie_three_random vmtm";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(MovieLandDataSource dataSource) throws IOException {
        this.dataSource = dataSource.getDataSource();
    }

    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_MOVIES, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom() {
        return  jdbcTemplate.query(SQL_GET_THREE_RANDOM_MOVIES, MOVIE_ROW_MAPPER);
    }

    public static RowMapper<Movie> getMovieRowMapper() {
        return MOVIE_ROW_MAPPER;
    }

    public static String getSqlGetAllMovies() {
        return SQL_GET_ALL_MOVIES;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
