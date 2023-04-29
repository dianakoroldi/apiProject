package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestData {

    private static final String PROPERTIES_FILE_PATH = "src/test/configuration.properties";

    public static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }


}

