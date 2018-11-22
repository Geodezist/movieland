package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.common.ParameterInfo;
import ua.com.bpgdev.movieland.common.SortingField;
import ua.com.bpgdev.movieland.common.SortingOrder;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.MovieDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.MovieRowMapper;
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

    public JdbcMovieDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(sqlGetAllMovies, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getAll(ParameterInfo parameterInfo) {
        if (parameterInfo == null) {
            return getAll();
        }
        String query = queryBuilder(sqlGetAllMovies, parameterInfo);
        return jdbcTemplate.query(query, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom() {
        return jdbcTemplate.query(sqlGetRandomMovies, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom(ParameterInfo parameterInfo) {
        if (parameterInfo == null) {
            return getThreeRandom();
        }
        String query = queryBuilder(sqlGetRandomMovies, parameterInfo);
        return jdbcTemplate.query(query, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return jdbcTemplate.query(sqlGetMoviesByGenreId, MOVIE_ROW_MAPPER, genreId);
    }

    @Override
    public List<Movie> getByGenreId(int genreId, ParameterInfo parameterInfo) {
        if (parameterInfo == null) {
            return getByGenreId(genreId);
        }
        String query = queryBuilder(sqlGetMoviesByGenreId, parameterInfo);
        return jdbcTemplate.query(query, MOVIE_ROW_MAPPER, genreId);
    }

    private String queryBuilder(String rawQuery, ParameterInfo parameterInfo) {
        SortingOrder ratingSortingOrder = parameterInfo.getParameters().get(SortingField.RATING);
        SortingOrder priceSortingOrder = parameterInfo.getParameters().get(SortingField.PRICE);
        StringBuilder queryStringBuilder = new StringBuilder(rawQuery);
        queryStringBuilder.append(" ORDER BY");
        if (ratingSortingOrder != null) {
            if (ratingSortingOrder == SortingOrder.DESC) {
                queryStringBuilder.append(" rating DESC");
            } else {
                throw new RuntimeException("Rating can be sorted only in DESC (descending) order! " +
                        "Was used - " + ratingSortingOrder);
            }
        } else if (priceSortingOrder != null) {
            if (priceSortingOrder == SortingOrder.ASC) {
                queryStringBuilder.append(" price ASC");
            } else if (priceSortingOrder == SortingOrder.DESC) {
                queryStringBuilder.append(" price DESC");
            } else {
                throw new RuntimeException("Price can be sorted only in ASC or DESC order! " +
                        "Was used - " + priceSortingOrder);
            }
        } else {
            throw new RuntimeException("Parameter is not found! Must be ratingSortingOrder or priceSortingOrder!");
        }
        return queryStringBuilder.append(", id ASC").toString();
    }

    public String getSqlGetAllMovies() {
        return sqlGetAllMovies;
    }

    public String getSqlGetMoviesByGenreId() {
        return sqlGetMoviesByGenreId;
    }
}
