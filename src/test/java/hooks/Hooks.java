package hooks;

import base.PlaywrightFactory;
import com.microsoft.playwright.Page;
import context.TestContext;
import enums.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import report.ExtentLogger;
import report.ExtentSpark;
import utils.PropertiesUtil;
import utils.ScreenshotUtil;

public class Hooks {

    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setup(Scenario scenario) {
        //initialize report
        ExtentSpark.initReports();
        ExtentSpark.createTest(scenario.getName());
        //initialize browser
        PlaywrightFactory.initBrowser();
        Page page = PlaywrightFactory.getPage();
        testContext.setPage(page);
        page.navigate(PropertiesUtil.getValue(ConfigProperties.BASEURL));

        ExtentLogger.info("Browser launched successfully");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String path =ScreenshotUtil.capture(scenario.getName());
            ExtentSpark.getTest().fail("Scenario Failed: " + scenario.getName()).addScreenCaptureFromPath(path);
        } else {
            ExtentLogger.pass("Scenario Passed: "  + scenario.getName());
        }
        ExtentSpark.flushReport();
        //quit browser
        PlaywrightFactory.quitBrowser();

    }
}