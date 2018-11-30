package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.bpgdev.movieland.entity.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcCountryDaoTest {
    private List<Country> expectedCountries = new ArrayList<>();


    @Before
    public void before() {
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
    public void getByMovieId() {
        int countriesCount = 4;
        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcCountryDao mockJdbcCountryDao = new JdbcCountryDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(any(), eq(JdbcCountryDao.COUNTRY_ROW_MAPPER), eq(1))).thenReturn(expectedCountries);

        List<Country> actualCountry = mockJdbcCountryDao.getByMovieId(1);
        assertEquals(countriesCount, actualCountry.size());
        assertEquals(expectedCountries, actualCountry);
    }
}