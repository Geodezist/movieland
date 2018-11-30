package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.movieland.dao.CountryDao;
import ua.com.bpgdev.movieland.entity.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:property/applicationContext-test.xml")
public class JdbcCountryDaoTest {
    @Autowired
    private CountryDao jdbcCountryDao;
    private List<Country> expectedCountries = new ArrayList<>();


    @Before
    public void before() {
        Country expectedCountry = new Country(45, "Германия");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(67, "Испания");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(68, "Италия");
        expectedCountries.add(expectedCountry);
        expectedCountry = new Country(157, "США");
        expectedCountries.add(expectedCountry);
    }


    @Test
    public void getIByMovieId() {
        List<Country> actualCountries = jdbcCountryDao.getByMovieId(21);
        assertEquals(expectedCountries, actualCountries);
    }
}