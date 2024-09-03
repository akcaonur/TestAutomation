package utils;

public class ConfigDto {
     String testType;
     String browserType;
     String baseUrl;

    public ConfigDto() {
        ConfigFileReader reader = new ConfigFileReader();
        this.testType = reader.readConfigFile().getProperty("testType");
        this.browserType = reader.readConfigFile().getProperty("browserType");
        this.baseUrl = reader.readConfigFile().getProperty("baseUrl");
    }

    public String getTestType() {
        return testType;
    }

    public String getBrowserType() {
        return browserType;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
