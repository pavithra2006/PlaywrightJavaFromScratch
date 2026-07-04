package constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.RunManager;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {

    private static final String PROJECT_PATH = System.getProperty("user.dir");

    private static final String LATEST_REPORT_FOLDER =
            PROJECT_PATH
                    + File.separator
                    + "test-output"
                    + File.separator
                    + "latest";

    private static final String RESOURCES_PATH =
            PROJECT_PATH + File.separator + "src"
                    + File.separator + "main"
                    + File.separator + "resources";

    private static final String CONFIG_FILE_PATH =
            RESOURCES_PATH
                    + File.separator + "config"
                    + File.separator + "config.properties";

    /**
     * GitHub Actions automatically sets CI=true.
     * Local execution -> false
     */
    private static final boolean IS_CI =
            Boolean.parseBoolean(System.getenv().getOrDefault("CI", "false"));

    /**
     * Local:
     * test-output/
     *
     * GitHub:
     * test-output/20260704_210530/
     */
    private static final String OUTPUT_FOLDER =
            PROJECT_PATH
                    + File.separator
                    + "test-output"
                    + File.separator
                    + RunManager.getRunId();

    private static final String REPORT_FOLDER =
            OUTPUT_FOLDER + File.separator + "report";

    private static final String SCREENSHOT_FOLDER =
            OUTPUT_FOLDER + File.separator + "screenshots";

    private static final String VIDEO_FOLDER =
            OUTPUT_FOLDER + File.separator + "videos";

    private static final String TRACE_FOLDER =
            OUTPUT_FOLDER + File.separator + "traces";

    private static final String HAR_FOLDER =
            OUTPUT_FOLDER + File.separator + "har";

    private static final String LOG_FOLDER =
            OUTPUT_FOLDER + File.separator + "logs";

    public static String getConfigFilePath() {
        return CONFIG_FILE_PATH;
    }

    public static String getOutputFolder() {
        return OUTPUT_FOLDER;
    }

    public static String getReportFolder() {
        return REPORT_FOLDER;
    }


    public static String getReportPath() {
        return REPORT_FOLDER
                + File.separator
                + "ExtentReport.html";
    }

    public static String getScreenshotPath(String fileName) {
        return SCREENSHOT_FOLDER
                + File.separator
                + fileName
                + ".png";
    }

    public static String getVideoFolder() {
        return VIDEO_FOLDER;
    }

    public static String getTracePath(String scenarioName) {
        return OUTPUT_FOLDER
                + File.separator
                + "traces"
                + File.separator
                + scenarioName
                + ".zip";
    }

    public static String getHarFilePath(String scenarioName) {
        return HAR_FOLDER
                + File.separator
                + scenarioName
                + ".har";
    }

    public static String getLogFile() {
        return LOG_FOLDER
                + File.separator
                + "automation.log";
    }

    /**
     * Only used by GitHub Actions
     */
    public static String getRunFolder() {
        return OUTPUT_FOLDER;
    }

    /**
     * Optional
     */
    public static boolean isCI() {
        return IS_CI;
    }

    public static String getLatestReportFolder() {
        return LATEST_REPORT_FOLDER;
    }

    public static String getLatestReportPath() {
        return LATEST_REPORT_FOLDER
                + File.separator
                + "index.html";
    }
}