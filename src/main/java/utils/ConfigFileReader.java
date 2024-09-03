package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {
    static Properties properties = new Properties();

    public Properties readConfigFile() {
        try (InputStream inputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }
}



