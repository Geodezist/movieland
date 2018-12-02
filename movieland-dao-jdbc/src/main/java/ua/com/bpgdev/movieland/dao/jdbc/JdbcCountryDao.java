package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import ua.com.bpgdev.movieland.dao.CountryDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.CountryRowMapper;
import ua.com.bpgdev.movieland.entity.Country;


@Repository
@Primary
public class JdbcCountryDao implements CountryDao {
    static final RowMapper<Country> COUNTRY_ROW_MAPPER = new CountryRowMapper();

    private String sqlGerAllCountries;
    private String sqlGetCountriesByMovieId;
    private JdbcTemplate jdbcTemplate;

    public JdbcCountryDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> getAll() {
        return jdbcTemplate.query(sqlGerAllCountries, COUNTRY_ROW_MAPPER);
    }

    @Override
    public List<Country> getByMovieId(int movieId) {
        return jdbcTemplate.query(sqlGetCountriesByMovieId, COUNTRY_ROW_MAPPER, movieId);
    }

    public String getSqlGerAllCountries() {
        return sqlGerAllCountries;
    }

    @Value("${sql.country.getAll}")
    public void setSqlGerAllCountries(String sqlGerAllCountries) {
        this.sqlGerAllCountries = sqlGerAllCountries;
    }

    public String getSqlGetCountriesByMovieId() {
        return sqlGetCountriesByMovieId;
    }

    @Value("${sql.country.getByMovieId}")
    public void setSqlGetCountriesByMovieId(String sqlGetCountriesByMovieId) {
        this.sqlGetCountriesByMovieId = sqlGetCountriesByMovieId;
    }
}
