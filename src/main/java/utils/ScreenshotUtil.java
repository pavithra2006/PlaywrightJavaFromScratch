package utils;

import base.PlaywrightFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String captureScreenshot(
            String fileName) throws IOException {

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