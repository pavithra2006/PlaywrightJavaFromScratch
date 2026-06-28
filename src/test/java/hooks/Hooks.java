package hooks;

import base.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import context.TestContext;
import enums.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import report.ExtentLogger;
import report.ExtentSpark;
import utils.PropertiesUtil;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    private final TestContext testContext;
    protected final Logger logger =
            LoggerFactory.getLogger(getClass());

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setup(Scenario scenario) {
        logger.info(
                "Thread Name: {} | Thread Id: {}",
                Thread.currentThread().getName(),
                Thread.currentThread().getId()
        );        //initialize report
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
    public void tearDown(Scenario scenario) throws IOException {
        String path;
        if (scenario.isFailed()) {
            path = ScreenshotUtil.capture(scenario.getName());
            ExtentSpark.getTest().fail("Scenario Failed: " + scenario.getName()).addScreenCaptureFromPath(path);
            PlaywrightFactory.getContext().tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get(
                                    "traces/" +
                                            scenario.getName() +
                                            ".zip")));
        } else {
            path = ScreenshotUtil.capture(scenario.getName());
            ExtentSpark.getTest().addScreenCaptureFromPath(path);
            ExtentLogger.pass("Scenario Passed: " + scenario.getName());
            PlaywrightFactory.getContext().tracing().stop();
            Files.deleteIfExists(testContext.getPage().video().path());
        }

        ExtentSpark.flushReport();
        //quit browser
        PlaywrightFactory.quitBrowser();

    }
}