package utils;

public class ScreenshotUtil {

    public static String captureScreenshot(
            String fileName){

        byte[] screenshot =
                PlaywrightFactory.getPage()
                        .screenshot();

        Path path =
                Paths.get("reports/screenshots/"
                        + fileName + ".png");

        Files.write(path,screenshot);

        return path.toString();
    }
}