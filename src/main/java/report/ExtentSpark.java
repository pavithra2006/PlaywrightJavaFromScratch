package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Playwright;
import enums.CategoryType;
import constants.FrameworkConstants;
import enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.PropertiesUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentSpark { // no need to extend it
    private static ExtentReports extent;

//    public static ExtentTest test; // causes thread local issue
    public static void initReports() {
        if (Objects.isNull(extent)) { // if we call this method twice null check avoids the problem
            extent = new ExtentReports(); // object for extent report
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getReportPath());
            //gets generated in root folder // obj for extent spark reporter class
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation test report");      // tab name
            spark.config().setReportName("Orange HRM portal testing"); // found in top right side
            extent.attachReporter(spark);

            extent.setSystemInfo("Browser", PropertiesUtil.getValue(ConfigProperties.BROWSER));
            extent.setSystemInfo("Environment", PropertiesUtil.getValue(ConfigProperties.ENVIRONMENT));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java version", System.getProperty("java.version"));
            extent.setSystemInfo("Playwright version", Playwright.class.getPackage().getImplementationVersion());
        }
    }
    public static void createTest(String testName) {
        ExtentManager.setTest(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return ExtentManager.getTest();
    }

    public static void flushReport() {
        if (extent != null) {

            extent.flush(); // flush ExtentReport variable
        }
    }
}
