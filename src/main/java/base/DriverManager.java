package base;

import io.cucumber.java.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ConfigDto;

public class DriverManager {



    @BeforeAll
    public static void beforeAll() {
        ChromeDriver driver = new ChromeDriver();
        ConfigDto config = new ConfigDto();

    }
}
