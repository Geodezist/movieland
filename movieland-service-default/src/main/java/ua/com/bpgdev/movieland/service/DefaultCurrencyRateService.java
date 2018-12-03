package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.CurrencyRateDao;
import ua.com.bpgdev.movieland.entity.CurrencyRate;

@Service
public class DefaultCurrencyRateService implements CurrencyRateService {
    private CurrencyRateDao currencyRateDao;

    public DefaultCurrencyRateService(@Autowired CurrencyRateDao currencyRateDao) {
        this.currencyRateDao = currencyRateDao;
    }

    @Override
    public CurrencyRate getRateByCode(String currencyCode)  {
        return currencyRateDao.getRateByCode(currencyCode);
    }
}
