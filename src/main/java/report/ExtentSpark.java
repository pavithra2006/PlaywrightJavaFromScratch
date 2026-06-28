package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import enums.CategoryType;
import constants.FrameworkConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
        }
    }
    public static void createTest(String testName) {
        ExtentManager.setTest(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return ExtentManager.getTest();
    }

    public static void flushReport() {
        extent.flush(); // flush ExtentReport variable
        ExtentManager.unloadTest(); // flush ExtentReport Threadlocal variable

    }

//    public static void flushReports() {
//        if (Objects.nonNull(extent)) {
//            extent.flush();
//        }
//        ExtentManager.unloadExtTest();
//
//    }
//
//    public static void createTest(String testCaseName) {
//        ExtentManager.setExtTest(extent.createTest(testCaseName));
//    }

//    public static void addAuthors(String[] authors) {
//        for (String author : authors) {
//            ExtentManager.getExtTest().assignAuthor(author);
//        }
//    }
//
//    public static void addCategories(CategoryType[] categories) {
//        for (CategoryType category : categories) {
//            ExtentManager.getExtTest().assignCategory(category.toString());
//        }
//    }
}
