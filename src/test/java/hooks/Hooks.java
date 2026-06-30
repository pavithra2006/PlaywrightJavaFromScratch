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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
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

        logger.info("******************** TEST START ********************");

        ExtentSpark.createTest(
                scenario.getName()
                        + " | "
                        + Thread.currentThread().getName());

        PlaywrightFactory.initBrowser(ConfigProperties.BROWSER);

        Page page = PlaywrightFactory.getPage();

        testContext.setPage(page);

        page.navigate(
                PropertiesUtil.getValue(ConfigProperties.BASEURL));

        ExtentManager.getTest()
                .assignDevice(PropertiesUtil.getValue(ConfigProperties.BROWSER));

        ExtentLogger.info("Browser launched successfully");
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {

        String screenshot =
                ScreenshotUtil.capture(scenario.getName());

        if (scenario.isFailed()) {

            ExtentSpark.getTest()
                    .fail("Scenario Failed")
                    .addScreenCaptureFromPath(screenshot);

            PlaywrightFactory.getContext()
                    .tracing()
                    .stop(
                            new Tracing.StopOptions()
                                    .setPath(
                                            Paths.get(
                                                    FrameworkConstants.getTracePath(
                                                            scenario.getName()))));

        } else {

            ExtentLogger.pass("Scenario Passed");

            ExtentSpark.getTest()
                    .addScreenCaptureFromPath(screenshot);

            PlaywrightFactory.getContext()
                    .tracing()
                    .stop();
        }

        PlaywrightFactory.quitBrowser();

        if (!scenario.isFailed()) {
            Files.deleteIfExists(testContext.getPage().video().path());
        }

        ExtentManager.unloadTest(); // flush ExtentReport Threadlocal variable

        MDC.clear();

        logger.info("******************** TEST END ********************");
    }
}