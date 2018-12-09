package ua.com.bpgdev.movieland.dao.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ua.com.bpgdev.movieland.entity.CurrencyRate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CurrencyRateDeSerializer extends JsonDeserializer<CurrencyRate> {

    @Override
    public CurrencyRate deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String currencyNameNative = jsonNode.get("txt").asText();
        String currencyCode = jsonNode.get("cc").asText();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US);
        LocalDate exchangeDate = LocalDate.parse(jsonNode.get("exchangedate").asText(), dateTimeFormatter);
        BigDecimal rate = BigDecimal.valueOf(jsonNode.get("rate").asDouble());

        return new CurrencyRate(currencyNameNative, rate, currencyCode, exchangeDate);
    }
}
