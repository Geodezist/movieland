package ua.com.bpgdev.movieland.util;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class InputStreamFromFileFactoryTest {

    @Test
    public void testGetInputStreamFromFile() throws IOException {
        InputStream inputStream = new InputStreamFromFileFactory().
                getInputStreamFromFile("property/logback.properties");
        assertNotNull(inputStream);
        inputStream.close();

        inputStream = new InputStreamFromFileFactory().
                getInputStreamFromFile("\\src\\test\\resources\\logback-test.xml");
        assertNotNull(inputStream);
        inputStream.close();
    }
}