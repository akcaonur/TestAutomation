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
    @FindBy(xpath = "//*[@href='/dorf2.php']")
    WebElement koyMerkezi2;

    @FindBy(xpath = "//*[@data-name='Köşk']")
    WebElement Kosk;

    @FindBy(xpath = "//button[contains(@value, 'Bu seviyeye')]")
    WebElement seviyeArttır;

    @FindBy(xpath = "(//*[@href='/hero/adventures' ])[1]")
    WebElement maceralar;

    @FindBy(xpath = "(//*[@class='borderGap adventureList']//button)[1]")
    WebElement maceraGonder;
    @FindBy(xpath = "(//*[text()='Tüm maceralara çıktın; daha fazlası için daha sonra tekrar gel!']")
    WebElement maceraBulunamadı;


    @FindBy(xpath = "//*[contains(text(),'Yağma listesi')]")
    WebElement yagmaListesi;

    @FindBy(xpath = "//*[text()='Tüm yağma listelerini başlat']")
    WebElement yagmaListesiBaslat;

    @FindBy(xpath = "//*[@data-name='Askeri Üs']")
    WebElement AskeriÜs;

    @FindBy(xpath = "//*[contains(@value,'Bu seviyeye geliştir:')]")
    WebElement Yükselt;


    @Given("Giriş Yap")
    public void login() {
        loginBtn.click();
        usernameField.isDisplayed();
        usernameField.sendKeys(configReader.getProperty("username"));
        passwordField.isDisplayed();
        passwordField.sendKeys(configReader.getProperty("password"));
        girisBtn.click();
        wait.until(ExpectedConditions.visibilityOf(hemenOynaBtn)).click();
    }

    @Given("macera gonder.")
    public void play() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(maceralar)).click();
        try {
             wait.until(ExpectedConditions.visibilityOf(maceraGonder)).click();
        } catch (Exception e) {
            System.out.println("macera yok");
        }
    }

    @Given("yağma listesi gönder")
    public void sendTroops() {
        koyMerkezi.click();
        wait.until(ExpectedConditions.visibilityOf(AskeriÜs)).click();
        wait.until(ExpectedConditions.visibilityOf(yagmaListesi)).click();;
        wait.until(ExpectedConditions.visibilityOf(yagmaListesiBaslat)).click();
    }

    @Given("Koşku (.*) seviyeye bas.$")
    public void koskArttırma(int lvl) {
        wait.until(ExpectedConditions.visibilityOf(koyMerkezi2)).click();
        wait.until(ExpectedConditions.visibilityOf(Kosk)).click();
        try {
            if (wait.until(ExpectedConditions.visibilityOf(Yükselt)).isDisplayed()) {
                if(Yükselt.getAttribute("value").equals("Bu seviyeye geliştir: "+lvl)){

                }else {
                    System.out.println("Yükseltme Başarılı");
                    wait.until(ExpectedConditions.visibilityOf(Yükselt)).click();
                }

            } else {
                System.out.println("Seviye basılamıyor.Hammadde yok.");

            }
        }catch (Exception e){

        }
    }
}
