package utilities;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "path/to/your_feature.feature")
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        Object[][] data = new Object[50][];
        for (int i = 0; i < 2; i++) {
            data[i] = new Object[] { "Iteration " + (i + 1) };
        }
        return data;
    }
}