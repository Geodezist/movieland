package ua.com.bpgdev.movieland.dao;

import ua.com.bpgdev.movieland.entity.CurrencyRate;

public interface CurrencyRateDao {
    CurrencyRate getRateByCode(String currencyCode);
}
