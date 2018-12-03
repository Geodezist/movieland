package ua.com.bpgdev.movieland.common;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CurrencyCode {
    EUR("EUR"),
    USD("USD");

    private String currencyCode;
    private static Map<String, CurrencyCode> CurrencyCodes = Stream.of(values()).
            collect(Collectors.toMap(CurrencyCode::getCurrencyCode, value -> value));

    CurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public static CurrencyCode valueOfCurrencyCode(String currencyCode){
        CurrencyCode result = CurrencyCodes.get(currencyCode.toUpperCase().trim());
        if (result == null){
            throw new IllegalArgumentException("Invalid value \"" + currencyCode + "\" for enum " +
                    SortingOrder.class.getName());
        }
        return result;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
