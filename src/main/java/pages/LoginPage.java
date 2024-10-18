package pages;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(xpath = "(//*[@class='textButtonV2 buttonFramed startFarmList rectangle withText green'])[1]")
    WebElement yagmaListesiBaslat2;

    @FindBy(xpath = "//*[@data-name='Askeri Üs']")
    WebElement AskeriÜs;

    @FindBy(xpath = "//*[contains(@value,'Bu seviyeye geliştir:')]")
    WebElement Yükselt;

    @FindBy(xpath = "(//*[@preserveAspectRatio='none'])[1]")
    WebElement YagmaListesiAskerBitti;

    @FindBy(xpath = "//*[@value='Tut']")
    WebElement FestivalBaşlat;

    @FindBy(xpath = "//*[@data-name='Belediye']")
    WebElement Belediye;

    @FindBy(xpath = "//*[@class='village resourceView ']")
    WebElement KoyKaynak;


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
        wait.until(ExpectedConditions.visibilityOf(yagmaListesi)).click();

        wait.until(ExpectedConditions.visibilityOf(yagmaListesiBaslat)).click();
    }

    @Given("Koşku (.*) seviyeye bas.$")
    public void koskArttırma(int lvl) {
        wait.until(ExpectedConditions.visibilityOf(koyMerkezi2)).click();
        wait.until(ExpectedConditions.visibilityOf(Kosk)).click();
        try {
            if (wait.until(ExpectedConditions.visibilityOf(Yükselt)).isDisplayed()) {
                if (Yükselt.getAttribute("value").equals("Bu seviyeye geliştir: " + (lvl+1))) {

                } else {
                    System.out.println("Yükseltme Başarılı");
                    wait.until(ExpectedConditions.visibilityOf(Yükselt)).click();
                }

            } else {
                System.out.println("Seviye basılamıyor.Hammadde yok.");

            }
        } catch (Exception e) {

        }
    }

    @Given("(.*) dakika beklenir.$")
    public void waitSec(int lvl) throws InterruptedException {
        Thread.sleep(Duration.ofMinutes(lvl));
    }

    @Given("Yağma gönder2.$")
    public void sendYagma2() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement YagmaListesiVahalar;
        WebElement YagmaListesiVaha;
        koyMerkezi.click();
        AskeriÜs.click();
        yagmaListesi.click();
        System.out.println("0");
        for (int i = 1; i <= 40; i++) {
            YagmaListesiVahalar = driver.findElement(By.xpath("(//*[@class='slot  '])[" + i + "]"));

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", YagmaListesiVahalar);
            System.out.println("1");
            try {
                WebElement element = driver.findElement(By.xpath("(//*[@class='slot  '])[" + i + "]//td//i[@class='attack_small']"));
            } catch (Exception e) {
                System.out.println("Hata" + e.getMessage());
                driver.findElement(By.xpath("(//*[@class='slot  '])[" + i + "]//td//input")).click();
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", yagmaListesiBaslat2);
                yagmaListesiBaslat2.click();
                try {
                   Boolean b = wait.until(ExpectedConditions.visibilityOf(YagmaListesiAskerBitti)).isDisplayed();
                   if (b.equals(true)){
                       break;
                   };
                }catch (Exception exception){

                }
            }
        }

    }
    @Given("Festival Başlat.$")
    public void startFest()   {
        wait.until(ExpectedConditions.visibilityOf(koyMerkezi2)).click();
        wait.until(ExpectedConditions.visibilityOf(Belediye)).click();

        try {
            FestivalBaşlat.click();
        }catch (Exception e){

        }
    }
}
