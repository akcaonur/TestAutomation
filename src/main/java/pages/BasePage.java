package pages;

import config.ConfigReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;


public class BasePage {
    WebDriver driver = DriverManager.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    ConfigReader configReader = new ConfigReader();

    public boolean waitUntilElementVisible(WebElement elementFindBy) {

        try {
            FluentWait<WebDriver> wait = (new WebDriverWait(driver, Duration.ofSeconds(1))).pollingEvery(Duration.ofSeconds(1L)).withTimeout(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(elementFindBy));
            return true;
        }catch (Exception e) {
            return false;
        }

    }
}
