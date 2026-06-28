package hooks;

import base.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import constants.FrameworkConstants;
import context.TestContext;
import enums.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import report.ExtentLogger;
import report.ExtentManager;
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
        MDC.put("scenario", scenario.getName());
        logger.info("********************TEST CASE START***************************");

//        logger.info( "Thread Name: {} | Thread Id: {}",Thread.currentThread().getName(),Thread.currentThread().getId());        //initialize report
        ExtentSpark.initReports();
        ExtentSpark.createTest(scenario.getName());
        //initialize browser
        PlaywrightFactory.initBrowser(ConfigProperties.BROWSER);
        Page page = PlaywrightFactory.getPage();
        testContext.setPage(page);
        page.navigate(PropertiesUtil.getValue(ConfigProperties.BASEURL));

        ExtentManager.getTest().assignDevice(String.valueOf(ConfigProperties.BROWSER));
        ExtentLogger.info("Browser launched successfully");
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        String path;
        if (scenario.isFailed()) {
            path = ScreenshotUtil.capture(scenario.getName());
            ExtentSpark.getTest().fail("Scenario Failed: " + scenario.getName()).addScreenCaptureFromPath(path);
            PlaywrightFactory.getContext().tracing().stop( new Tracing.StopOptions().setPath(Paths.get(FrameworkConstants.getTracePath(scenario.getName()))));
        } else {
            path = ScreenshotUtil.capture(scenario.getName());
            ExtentSpark.getTest().addScreenCaptureFromPath(path);
            ExtentLogger.pass("Scenario Passed: " + scenario.getName());
            PlaywrightFactory.getContext().tracing().stop();

        }
        ExtentSpark.flushReport();//quit browser
        PlaywrightFactory.quitBrowser();
        if (!scenario.isFailed()) { // only after quitting page/ context - playwright will remove handle on video
            Files.deleteIfExists(testContext.getPage().video().path());
        }
        MDC.clear();
        logger.info("********************TEST CASE END***************************");
    }
}