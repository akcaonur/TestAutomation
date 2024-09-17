package pages;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;

public class LoginPage extends BasePage {
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public LoginPage() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "login")
    WebElement loginBtn;
    @FindBy(name = "name")
    WebElement usernameField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(xpath = "//button/*[text()='Giriş']")
    WebElement girisBtn;
    @FindBy(xpath = "(//*[text()='Hemen oyna'])[1]")
    WebElement hemenOynaBtn;
    @FindBy(xpath = "//*[@class='village buildingView ']")
    WebElement villageView;
    @FindBy(xpath = "//*[@data-name='Sığınak']")
    WebElement SınakView;


    @Given("Giriş Yap")
    public void login()  {
        loginBtn.click();
        usernameField.isDisplayed();
        usernameField.sendKeys(configReader.getProperty("username"));
        passwordField.isDisplayed();
        passwordField.sendKeys(configReader.getProperty("password"));
        girisBtn.click();
        WebElement element = wait.until(ExpectedConditions.visibilityOf(hemenOynaBtn));
        element.click();
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(villageView));
        element1.isDisplayed();




    }
}
