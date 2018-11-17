package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.GenreRowMapper;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

public class JdbcGenreDao implements GenreDao {
    private static final RowMapper<Genre> GENRE_ROW_MAPPER = new GenreRowMapper();
    @Value("${sql.sql_get_all_genres}")
    private String sqlGetAllGenres;
    private JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(sqlGetAllGenres, GENRE_ROW_MAPPER);
    }

    public static RowMapper<Genre> getGenreRowMapper() {
        return GENRE_ROW_MAPPER;
    }

    public String getSqlGetAllGenres() {
        return sqlGetAllGenres;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
