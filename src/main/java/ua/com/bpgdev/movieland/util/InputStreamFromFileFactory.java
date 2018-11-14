package ua.com.bpgdev.movieland.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputStreamFromFileFactory implements Closeable {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private InputStream inputStream;

    public InputStream getInputStreamFromFile(String fileName) throws FileNotFoundException {
        LOGGER.debug("Start loading Data Source configuration file {}", fileName);

        Path filePath = Paths.get(".", fileName);
        String absoluteFileName = filePath.normalize().toAbsolutePath().toString();
        File file = new File(absoluteFileName);

        if (file.isFile()){
            inputStream = new FileInputStream(file);
            LOGGER.debug("Got Data Source config from EXTERNAL file: {}", absoluteFileName);
        } else {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            LOGGER.debug("Got Data Source config from CLASSPATH file: {}", fileName);
        }
        if (inputStream == null) {
            LOGGER.error("Input stream from file {} is empty", fileName);
            throw new FileNotFoundException("File " + absoluteFileName + " or " + fileName + " does not found.");
        }
        return inputStream;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}

