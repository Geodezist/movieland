package ua.com.bpgdev.movieland.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.CurrencyRateDao;
import ua.com.bpgdev.movieland.entity.CurrencyRate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
@Primary
public class CacheCurrencyRateDao implements CurrencyRateDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String[] initialCurrencies = {"USD", "EUR"};

    private CurrencyRateDao nonCachedCurrencyRateDao;
    private volatile Map<String, CurrencyRate> currencyRates = new HashMap<>();


    public CacheCurrencyRateDao(@Qualifier("restCurrencyRateDao") CurrencyRateDao nonCachedCurrencyRateDao) {
        this.nonCachedCurrencyRateDao = nonCachedCurrencyRateDao;
    }

    @Scheduled(cron = "${cache.currencyRateInvalidateStartTime}")
    @PostConstruct
    private void invalidateCurrencyRateCache() {
        LOGGER.debug("Validating cache for Currency Rates.");
        Map<String, CurrencyRate> refreshedCurrencyRates = new HashMap<>();

        for (String currency :
                initialCurrencies) {
            refreshedCurrencyRates.put(currency, nonCachedCurrencyRateDao.getRateByCode(currency));
        }

        currencyRates = refreshedCurrencyRates;
    }

    @Override
    public CurrencyRate getRateByCode(String currencyCode) {
        LOGGER.debug("Getting Currency Rate for {} from cache.", currencyCode);
        CurrencyRate cachedCurrencyRate = currencyRates.get(currencyCode);

        return new CurrencyRate(
                cachedCurrencyRate.getCurrencyNameNative(),
                cachedCurrencyRate.getRate(),
                cachedCurrencyRate.getCurrencyCode(),
                cachedCurrencyRate.getExchangeDate());
    }
}
