package base;

import com.microsoft.playwright.*;
import constants.FrameworkConstants;
import enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import report.ExtentLogger;
import report.ExtentManager;
import utils.PropertiesUtil;

import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlaywrightFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(PlaywrightFactory.class);

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>(); // cant be modified and used within class only
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initBrowser(ConfigProperties browserName) {
        playwright.set(Playwright.create());
        switch (PropertiesUtil.getValue(browserName)) {
            case "chromium":
                browser.set(playwright.get().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(PropertiesUtil.getValue(ConfigProperties.HEADLESS)))));
                break;
            case "firefox":
                browser.set(playwright.get().firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(PropertiesUtil.getValue(ConfigProperties.HEADLESS)))));
                break;
            case "safari":
                browser.set(playwright.get().webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(PropertiesUtil.getValue(ConfigProperties.HEADLESS)))));
                break;
        }
        logger.info(String.valueOf(browserName));
        context.set(browser.get().newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get(FrameworkConstants.getVideoFolder()))));
        context.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
        page.set(context.get().newPage());
    }

    //thread local variables are private final -so use getters
    public static Page getPage() {
        return page.get();
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static void quitBrowser() {
        if (page.get() != null) {
            page.get().close(); // removes pw references
            page.remove(); // removes threadlocal references
        }
        if (context.get() != null) {
            context.get().close();
            context.remove();
        }
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }

    }
}
//here page, browsercontext and browser --> all are not thread safe
