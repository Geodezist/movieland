package ua.com.bpgdev.movieland.dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import ua.com.bpgdev.movieland.entity.Country;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcCountryDaoTest {
    private List<Country> expectedCountries;

    @Before
    public void before() {
        expectedCountries = new ArrayList<>();
        Country expectedCountry = new Country(1, "Абхазия");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(2, "Австралия");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(3, "Австрия");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(4, "Азербайджан");
        expectedCountries.add(expectedCountry);
    }

    @Test
    public void testGetAll(){
        int countriesCount = 4;

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcCountryDao mockJdbcCountryDao = new JdbcCountryDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(
                mockJdbcCountryDao.getSqlGerAllCountries(),
                JdbcCountryDao.COUNTRY_ROW_MAPPER)).thenReturn(expectedCountries);

        List<Country> actualCountry = mockJdbcCountryDao.getAll();
        assertEquals(countriesCount, actualCountry.size());
        assertEquals(expectedCountries, actualCountry);
    }

    @Test
    public void testGetByMovieId() {
        int countriesCount = 4;
        int movieId = 1;

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcCountryDao mockJdbcCountryDao = new JdbcCountryDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(
                mockJdbcCountryDao.getSqlGetCountriesByMovieId(),
                JdbcCountryDao.COUNTRY_ROW_MAPPER,
                movieId)).thenReturn(expectedCountries);

        List<Country> actualCountry = mockJdbcCountryDao.getByMovieId(1);
        assertEquals(countriesCount, actualCountry.size());
        assertEquals(expectedCountries, actualCountry);
    }
}