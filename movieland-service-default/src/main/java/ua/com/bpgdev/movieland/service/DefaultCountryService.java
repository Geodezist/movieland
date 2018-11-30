package ua.com.bpgdev.movieland.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.bpgdev.movieland.dao.CountryDao;
import ua.com.bpgdev.movieland.entity.Country;

@Service
public class DefaultCountryService implements CountryService {
    private CountryDao countryDao;

    @Autowired
    public DefaultCountryService(CountryDao countryDao) {
    this.countryDao = countryDao;
    }

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }

    @Override
    public List<Country> getByMovieId(int movieId) {
        return countryDao.getByMovieId(movieId);
    }
}
