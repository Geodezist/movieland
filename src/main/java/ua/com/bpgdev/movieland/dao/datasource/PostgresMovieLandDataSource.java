package ua.com.bpgdev.movieland.dao.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.yaml.snakeyaml.Yaml;
import ua.com.bpgdev.movieland.util.InputStreamFromFileFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class PostgresMovieLandDataSource implements MovieLandDataSource {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private String configFileName = "property/datasource-property.yml";
    private DriverManagerDataSource dataSource;

    public PostgresMovieLandDataSource() {
        LOGGER.debug("Creating object with default configuration file");
    }

    public PostgresMovieLandDataSource(String configFileName) {
        LOGGER.debug("Creating object with custom configuration file: {}", configFileName);
        this.configFileName = configFileName;
    }

    @Override
    public DataSource getDataSource() throws IOException {
        if (dataSource == null) {

            Yaml yaml = new Yaml();
            DataSourceConfiguration configuration;

            try (InputStream inputStream = new InputStreamFromFileFactory().
                    getInputStreamFromFile(configFileName)) {
                configuration = yaml.loadAs(inputStream, DataSourceConfiguration.class);
            } catch (IOException e) {
                LOGGER.error("When getting configuration for Data Source", e);
                throw new IOException(e);
            }

            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(configuration.getDriverClassName());
            dataSource.setUrl(configuration.getUrl());
            dataSource.setUsername(configuration.getUsername());
            dataSource.setPassword(configuration.getPassword());
        }
        return dataSource;
    }
}
