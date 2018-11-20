package ua.com.bpgdev.movieland.dao.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MovieLandDataSource {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private HikariDataSource hikariDataSource;


    public MovieLandDataSource(){
        LOGGER.debug("Creating object with default configuration file");
        HikariConfig hikariConfig = new HikariConfig("/property/datasource.properties");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public MovieLandDataSource(String configFileName){
        LOGGER.debug("Creating object with custom configuration file: {}", configFileName);
        HikariConfig hikariConfig = new HikariConfig(configFileName);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    @Bean
    public DataSource getDataSource() {
        return hikariDataSource;
    }
}
