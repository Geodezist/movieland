package ua.com.bpgdev.movieland.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ua.com.bpgdev.movieland.dao.jdbc.JdbcCountryDao;
import ua.com.bpgdev.movieland.entity.Country;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultCountryServiceTest {
    private List<Country> expectedCountries;

    @Before
    public void before() {
        expectedCountries = new ArrayList<>();
        Country expectedCountry = new Country(5, "Албания");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(6, "Алжир");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(7, "Ангола");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(8, "Андорра");
        expectedCountries.add(expectedCountry);
    }

    @Test
    public void testGetAll() {
        int countriesCount = 4;

        JdbcCountryDao mockJdbcCountryDao = mock(JdbcCountryDao.class);
        DefaultCountryService mockDefaultCountryService = new DefaultCountryService(mockJdbcCountryDao);
        when(mockJdbcCountryDao.getAll()).thenReturn(expectedCountries);

        List<Country> actualCountries = mockDefaultCountryService.getAll();

        assertEquals(countriesCount, actualCountries.size());
        assertEquals(expectedCountries, actualCountries);
    }

    @Test
    public void getByMovieId() {
        int movieId = 1;
        int countriesCount = 4;

        JdbcCountryDao mockJdbcCountryDao = mock(JdbcCountryDao.class);
        DefaultCountryService mockDefaultCountryService = new DefaultCountryService(mockJdbcCountryDao);
        when(mockJdbcCountryDao.getByMovieId(movieId)).thenReturn(expectedCountries);

        List<Country> actualCountries = mockDefaultCountryService.getByMovieId(movieId);

        assertEquals(countriesCount, actualCountries.size());
        assertEquals(expectedCountries, actualCountries);
    }

}