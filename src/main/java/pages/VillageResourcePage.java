package pages;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;
import java.util.List;

public class VillageResourcePage extends BasePage{

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public VillageResourcePage() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "resourceFieldContainer")
    WebElement allresource;
    @FindBy(xpath = "//*[@value='Yapı ustası ile inşa et']")
    WebElement yapiUstasi;
    @FindBy(xpath = "//*[@class='section1']/button")
    WebElement upgrade;

    @Given("yükselt")
    public void upgradeResource() throws InterruptedException {
        List<WebElement> list = allresource.findElements(By.tagName("a"));
        for (int i = 0; i < list.size(); i++) {
            WebElement link = list.get(i);
            if(link.getAttribute("href").contains("/build.php?id=")){
                link.click();
                if(waitUntilElementVisible(yapiUstasi)){

                }else {
                    upgrade.click();
                    return;
                }
            }

        }

    }


}
