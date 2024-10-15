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

import java.sql.Time;
import java.sql.Timestamp;
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
    WebElement koyMerkezi;

    @FindBy(xpath = "//*[@data-name='Köşk']")
    WebElement Kosk;

    @FindBy(xpath = "//button[contains(@value, 'Bu seviyeye')]")
    WebElement seviyeArttır;

    @FindBy(xpath = "(//*[@href='/hero/adventures' ])[1]")
    WebElement maceralar;

    @FindBy(xpath = "(//*[@class='borderGap adventureList']//button)[1]")
    WebElement maceraGonder;

    @FindBy(xpath = "//*[contains(text(),'Yağma listesi')]")
    WebElement yagmaListesi;

    @FindBy(xpath = "//*[text()='Tüm yağma listelerini başlat']")
    WebElement yagmaListesiBaslat;

    @FindBy(xpath = "//*[@data-name='Askeri Üs']")
    WebElement AskeriÜs;


    @Given("Giriş Yap")
    public void login() {
        loginBtn.click();
        usernameField.isDisplayed();
        usernameField.sendKeys(configReader.getProperty("username"));
        passwordField.isDisplayed();
        passwordField.sendKeys(configReader.getProperty("password"));
        girisBtn.click();
        WebElement element = wait.until(ExpectedConditions.visibilityOf(hemenOynaBtn));
        element.click();


    }

    @Given("macera gonder.")
    public void play() throws InterruptedException {


        WebElement element4 = wait.until(ExpectedConditions.visibilityOf(maceralar));
        element4.isDisplayed();
        element4.click();
        WebElement element5 = wait.until(ExpectedConditions.visibilityOf(maceraGonder));
        element5.isDisplayed();
        element5.click();




        Thread.sleep(60000);

    }
    @Given("yağma listesi gönder")
    public void sendTroops(){

        koyMerkezi.click();
        WebElement element6 = wait.until(ExpectedConditions.visibilityOf(AskeriÜs));
        AskeriÜs.click();
        WebElement element7 = wait.until(ExpectedConditions.visibilityOf(yagmaListesi));
        element7.click();
        WebElement element8 = wait.until(ExpectedConditions.visibilityOf(yagmaListesiBaslat));
        yagmaListesiBaslat.click();


    }
}
