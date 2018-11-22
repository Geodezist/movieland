package ua.com.bpgdev.movieland.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
public class CacheGenreDao implements GenreDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private GenreDao nonCacheGenreDao;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private List<Genre> cachedGenres;

    public CacheGenreDao(@Qualifier("jdbcGenreDao") GenreDao nonCacheGenreDao) {
        this.nonCacheGenreDao = nonCacheGenreDao;
    }

    @Scheduled(fixedDelayString = "${cache.genresInvalidateTimeout}",
            initialDelayString = "${cache.genresInvalidateTimeout}")
    @PostConstruct
    private void invalidateGenreCache() {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        List<Genre> refreshedCacheGenres;
        try {
            LOGGER.debug("Validating cachedGenres.");
            refreshedCacheGenres = nonCacheGenreDao.getAll();
        } finally {
            writeLock.unlock();
        }
        cachedGenres = refreshedCacheGenres;
    }

    @Override
    public List<Genre> getAll() {
        LOGGER.debug("Returning genres from cache.");
        return new ArrayList<>(cachedGenres);
    }
}
