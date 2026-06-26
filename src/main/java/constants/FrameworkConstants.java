package constants;

import enums.ConfigProperties;
import utils.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//no object can be created
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/main/resources";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
    //these variables cant be modified from anywhere, only through getters we can get value
    private static final String EXTENTREPORTSFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output";

    private static String extentReportFilesPath = "";

//    private static final int EXPLICITWAIT = 20;
    public static String getConfigFilePath() {
        //getter method
        return CONFIGFILEPATH;
    }

//    public static int getExplicitWait() {
//        return EXPLICITWAIT;
//    }

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
//        if (PropertiesUtil.getValue(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no"))
//            return EXTENTREPORTSFOLDERPATH + "/" + System.currentTimeMillis() + "/index.html";
//        else
//            return EXTENTREPORTSFOLDERPATH + "/" + "index.html";
//    }

}
