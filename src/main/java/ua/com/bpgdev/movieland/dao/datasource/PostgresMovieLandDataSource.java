package ua.com.bpgdev.movieland.dao.datasource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.yaml.snakeyaml.Yaml;
import ua.com.bpgdev.movieland.util.InputStreamFromFileFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class PostgresMovieLandDataSource implements MovieLandDataSource {

    private String configFileName = "property/datasource-property.yml";

    public PostgresMovieLandDataSource() {
    }

    public PostgresMovieLandDataSource(String configFileName){
        this.configFileName = configFileName;
    }

    @Override
    public DataSource getDataSource() throws IOException {
        Yaml yaml = new Yaml();
        DataSourceConfiguration configuration;

        try (InputStream inputStream = new InputStreamFromFileFactory().
                getInputStreamFromFile(configFileName)) {
            configuration = yaml.loadAs(inputStream, DataSourceConfiguration.class);
        } catch (IOException e) {
            throw new IOException(e);
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(configuration.getDriverClassName());
        dataSource.setUrl(configuration.getUrl());
        dataSource.setUsername(configuration.getUsername());
        dataSource.setPassword(configuration.getPassword());
        return dataSource;
    }
}
