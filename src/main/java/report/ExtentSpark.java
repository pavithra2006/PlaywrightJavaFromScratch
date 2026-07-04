package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Playwright;
import constants.FrameworkConstants;
import enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.PropertiesUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentSpark { // no need to extend it
    private static ExtentReports extent;
    private static  ExtentSparkReporter spark;

    //    public static ExtentTest test; // causes thread local issue
    public static void initReports() {
        if (Objects.isNull(extent)) { // if we call this method twice null check avoids the problem
            extent = new ExtentReports(); // object for extent report
            spark = new ExtentSparkReporter(FrameworkConstants.getReportPath());
            //gets generated in root folder // obj for extent spark reporter class
            getSparkReporter();
            extent.attachReporter(spark);
            addSystemInfo();
        }
    }

    private static void getSparkReporter(){
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation test report");      // tab name
        spark.config().setReportName("Orange HRM portal testing"); // found in top right side
    }
    private static void addSystemInfo() {
        extent.setSystemInfo("Browser", System.getProperty("browser",PropertiesUtil.getValue(ConfigProperties.BROWSER)));
        extent.setSystemInfo("Environment", PropertiesUtil.getValue(ConfigProperties.ENVIRONMENT));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java version", System.getProperty("java.version"));
        extent.setSystemInfo("Playwright version", Playwright.class.getPackage().getImplementationVersion());
    }

    public static void createTest(String testName) {
        ExtentManager.setTest(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return ExtentManager.getTest();
    }

    public static void flushReport() {

        if (extent != null) {
            extent.flush();
        }

        if (!FrameworkConstants.isCI()) {
            return;
        }

        try {

            Path latest = Paths.get(FrameworkConstants.getLatestReportFolder());

            Files.createDirectories(latest);

            copyDirectory(
                    Paths.get(FrameworkConstants.getReportFolder()),
                    latest.resolve("report"));

            copyDirectory(
                    Paths.get(FrameworkConstants.getScreenshotFolder()),
                    latest.resolve("screenshots"));

            copyDirectory(
                    Paths.get(FrameworkConstants.getVideoFolder()),
                    latest.resolve("videos"));

            copyDirectory(
                    Paths.get(FrameworkConstants.getHarFolder()),
                    latest.resolve("har"));

            copyDirectory(
                    Paths.get(FrameworkConstants.getTraceFolder()),
                    latest.resolve("traces"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyDirectory(Path source, Path destination) throws IOException {

        if (!Files.exists(source))
            return;

        Files.walk(source).forEach(path -> {

            try {

                Path target = destination.resolve(source.relativize(path));

                if (Files.isDirectory(path)) {

                    Files.createDirectories(target);

                } else {

                    Files.copy(
                            path,
                            target,
                            StandardCopyOption.REPLACE_EXISTING);

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
