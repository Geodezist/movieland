package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.entity.CurrencyRate;

public interface CurrencyRateService {
    CurrencyRate getRateByCode(String currencyCode);
}
