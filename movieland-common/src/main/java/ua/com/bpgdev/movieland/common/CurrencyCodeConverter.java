package ua.com.bpgdev.movieland.common;

import java.beans.PropertyEditorSupport;

public class CurrencyCodeConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        CurrencyCode currencyCode = CurrencyCode.valueOfCurrencyCode(text);
        setValue(currencyCode);
    }
}
