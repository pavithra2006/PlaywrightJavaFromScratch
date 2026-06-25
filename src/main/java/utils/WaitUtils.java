package utils;

import com.microsoft.playwright.Page;

public class WaitUtils {

    private WaitUtils() {}

    public static void waitForPageLoad(Page page) {

        page.waitForLoadState();
    }

    public static void waitForSelector(Page page,
                                       String locator) {

        page.locator(locator).waitFor();
    }

    public static void waitForTimeout(Page page,
                                      int milliseconds) {

        page.waitForTimeout(milliseconds);
    }
}