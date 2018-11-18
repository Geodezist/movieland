package ua.com.bpgdev.movieland.dao.datasource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface MovieLandDataSource {
    DataSource getDataSource() throws IOException;
}
