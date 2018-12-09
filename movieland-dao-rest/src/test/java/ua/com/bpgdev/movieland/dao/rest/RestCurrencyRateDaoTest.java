package ua.com.bpgdev.movieland.dao.rest;

import org.junit.Test;
import ua.com.bpgdev.movieland.entity.CurrencyRate;

import static org.junit.Assert.*;

public class RestCurrencyRateDaoTest {

    @Test
    public void testGetRateByCode() {

        CurrencyRate currencyRate;

        RestCurrencyRateDao restCurrencyRateDao =
                new RestCurrencyRateDao("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange"
                        + "?valcode={currencyCode}&date={currencyRateDate}{&jsonFormatAttribute}");

        currencyRate = restCurrencyRateDao.getRateByCode("EUR");
        assertEquals("EUR", currencyRate.getCurrencyCode());
    }
}