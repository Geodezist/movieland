package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.CountryDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.CountryRowMapper;
import ua.com.bpgdev.movieland.entity.Country;

import java.util.List;

@Repository
@Primary
public class JdbcCountryDao implements CountryDao {
    static final RowMapper<Country> COUNTRY_ROW_MAPPER = new CountryRowMapper();
    @Value("${sql.country.getByMovieId}")
    private String sqlGetCountriesByMovieID;
    private JdbcTemplate jdbcTemplate;

    public JdbcCountryDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> getByMovieId(int movieId) {
        return jdbcTemplate.query(sqlGetCountriesByMovieID, COUNTRY_ROW_MAPPER, movieId);
    }
}
