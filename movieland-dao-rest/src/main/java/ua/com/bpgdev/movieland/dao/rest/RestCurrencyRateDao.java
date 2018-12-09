package ua.com.bpgdev.movieland.dao.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.movieland.dao.CurrencyRateDao;
import ua.com.bpgdev.movieland.dao.rest.deserializer.CurrencyRateDeSerializer;
import ua.com.bpgdev.movieland.entity.CurrencyRate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Repository
public class RestCurrencyRateDao implements CurrencyRateDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.US);
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final SimpleModule SIMPLE_MODULE = new SimpleModule();
    private String currencyRateRestJsonLink;


    public RestCurrencyRateDao(@Value("${param.currencyRateRestLink}") String currencyRateRestJsonLink) {
        this.currencyRateRestJsonLink = currencyRateRestJsonLink;
    }

    @Override
    public CurrencyRate getRateByCode(String currencyCode) {
        String localDateTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        String finalUrl = currencyRateRestJsonLink
                .replace("{currencyCode}", currencyCode)
                .replace("{currencyRateDate}", localDateTime)
                .replace("{&jsonFormatAttribute}", "&json");

        ResponseEntity<String> response = REST_TEMPLATE.getForEntity(finalUrl, String.class);

        String actualJson = response.getBody();
        SIMPLE_MODULE.addDeserializer(CurrencyRate.class, new CurrencyRateDeSerializer());
        OBJECT_MAPPER.registerModule(SIMPLE_MODULE);

        TypeReference listCurrencyRate = new TypeReference<List<CurrencyRate>>() {
        };
        try {
            List<CurrencyRate> currencyRates = OBJECT_MAPPER.readValue(actualJson, listCurrencyRate);
            CurrencyRate currencyRate = currencyRates.get(0);
            LOGGER.info("Get currency rate data from {}", finalUrl);

            return currencyRate;
        } catch (IOException e) {
            LOGGER.error("Got error during reading currency rate data from {}", finalUrl, e);
            throw new RuntimeException(e);
        }
    }
}
