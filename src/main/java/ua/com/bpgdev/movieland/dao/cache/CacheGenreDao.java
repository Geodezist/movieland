package ua.com.bpgdev.movieland.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@EnableScheduling
//@PropertySource("classpath:property/application.properties")
public class CacheGenreDao implements GenreDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private GenreDao nonCacheGenreDao;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private List<Genre> cachedGenres;

    public CacheGenreDao(GenreDao nonCacheGenreDao) {
        this.nonCacheGenreDao = nonCacheGenreDao;
    }

    @Scheduled(fixedDelayString = "${cache.genresInvalidateTimeout}")
    @PostConstruct
    private void invalidateGenreCache() {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            LOGGER.debug("Validating cachedGenres.");
            cachedGenres = nonCacheGenreDao.getAll();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<Genre> getAll() {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            LOGGER.debug("Returning genres from cache.");
            return cachedGenres;
        } finally {
            readLock.unlock();
        }
    }
}
