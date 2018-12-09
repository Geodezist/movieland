package ua.com.bpgdev.movieland.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyRate {
    private String currencyNameNative; //txt
    private BigDecimal rate;
    private String currencyCode; //cc
    private LocalDate exchangeDate;

    public CurrencyRate(String currencyNameNative, BigDecimal rate, String currencyCode, LocalDate exchangeDate) {
        this.currencyNameNative = currencyNameNative;
        this.rate = rate;
        this.currencyCode = currencyCode;
        this.exchangeDate = exchangeDate;
    }

    public String getCurrencyNameNative() {
        return currencyNameNative;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

}
