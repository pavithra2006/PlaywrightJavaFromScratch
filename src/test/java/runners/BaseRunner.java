package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import report.ExtentSpark;

public class BaseRunner extends AbstractTestNGCucumberTests {
    //every scenario runs parallel
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeClass
    public void beforeClass() {
        ExtentSpark.initReports();
    }

    @AfterClass
    public void afterClass() {
        ExtentSpark.flushReport();
    }
}