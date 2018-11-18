package ua.com.bpgdev.movieland.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputStreamFromFileFactory implements Closeable {
    private InputStream inputStream;

    public InputStream getInputStreamFromFile(String fileName) throws FileNotFoundException {

        Path filePath = Paths.get(".", fileName);
        String absoluteFileName = filePath.normalize().toAbsolutePath().toString();
        File file = new File(absoluteFileName);

        inputStream = file.isFile() ?
                new FileInputStream(file) :
                this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null){
            throw new FileNotFoundException("File " + absoluteFileName + " or " + fileName + " does not found.");
        }
        return inputStream;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}

