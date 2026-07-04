package utils;

import base.PlaywrightFactory;
import com.microsoft.playwright.Page;
import constants.FrameworkConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenshotUtil {
    public static String capture(String fileName) {

        String path = FrameworkConstants.getScreenshotPath(fileName);

        PlaywrightFactory.getPage().screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(path))
                        .setFullPage(true));

        return path;
    }
}