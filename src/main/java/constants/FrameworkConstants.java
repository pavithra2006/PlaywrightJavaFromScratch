package constants;

import enums.ConfigProperties;
import utils.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.RunManager;

//no object can be created
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/main/resources";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
    //these variables cant be modified from anywhere, only through getters we can get value
    private static final String EXTENTREPORTSFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output";
    public static final String OUTPUT_FOLDER =System.getProperty("user.dir")+ "/test-output/"+ RunManager.getRunId();
    private static String extentReportFilesPath = "";

    //    private static final int EXPLICITWAIT = 20;
    public static String getConfigFilePath() {
        //getter method
        return CONFIGFILEPATH;
    }

//    public static String getExtentReportFilesPath() {
//        if (extentReportFilesPath.isEmpty()) {
//            extentReportFilesPath = getExtentReportsPath();
//            return extentReportFilesPath;
//        } else {
//            return extentReportFilesPath;
//        }
//    }
//
//    private static String getExtentReportsPath() {
//        return EXTENTREPORTSFOLDERPATH + "/" + System.currentTimeMillis() + "/index.html";
//    }

    public static String getReportPath() {
        return OUTPUT_FOLDER + "/report/ExtentReport.html";
    }

    public static String getScreenshotPath(String fileName) {
        return OUTPUT_FOLDER + "/screenshots/" + fileName + ".png";
    }

    public static String getTracePath(String scenarioName) {
        return OUTPUT_FOLDER + "/traces/" + scenarioName + ".zip";
    }

    public static String getVideoFolder() {
        return OUTPUT_FOLDER + "/videos/";
    }

    public static String getLogFile() {
        return OUTPUT_FOLDER + "/logs/automation.log";
    }
}
