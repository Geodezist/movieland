package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.junit.Test;
import ua.com.bpgdev.movieland.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("Test Country");

        Country expectedCountry = new Country(1, "Test Country");

        CountryRowMapper countryRowMapper = new CountryRowMapper();
        Country actualCountry = countryRowMapper.mapRow(mockResultSet, 1);

        assertEquals(expectedCountry, actualCountry);
    }
}