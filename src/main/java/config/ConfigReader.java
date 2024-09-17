package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    public ConfigReader() {
        properties = new Properties();
        try {
            // config.properties dosyasının yolunu belirtin
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Property değerlerini almak için bir metod
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
