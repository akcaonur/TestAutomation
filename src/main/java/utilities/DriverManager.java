package utilities;

import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;
    ConfigReader config = new ConfigReader();

    @Before
    public void setup(){
        driver = DriverManager.getDriver();
        driver.get(config.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();

        }
        return driver;
    }

    @After
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
