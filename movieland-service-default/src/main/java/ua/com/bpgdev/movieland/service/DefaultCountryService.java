package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.CountryDao;
import ua.com.bpgdev.movieland.entity.Country;

import java.util.List;

@Service
@Primary
public class DefaultCountryService implements CountryService {
    private CountryDao countryDao;

    public DefaultCountryService(@Autowired CountryDao countryDao) {
    this.countryDao = countryDao;
    }

    @Override
    public List<Country> getByMovieId(int movieId) {
        return countryDao.getByMovieId(movieId);
    }
}
