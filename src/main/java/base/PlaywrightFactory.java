package base;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;
import constants.FrameworkConstants;
import enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesUtil;

import java.nio.file.Paths;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlaywrightFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(PlaywrightFactory.class);

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>(); // cant be modified and used within class only
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    private static BrowserType getBrowserType(String browserName) {
        logger.info(browserName.toLowerCase());
        return switch (browserName.toLowerCase()) {
            case "chromium" -> playwright.get().chromium();
            case "firefox" -> playwright.get().firefox();
            case "safari", "webkit" -> playwright.get().webkit();
            default -> throw new IllegalArgumentException("Unsupported Browser : " + browserName);
        };
    }

    public static void initBrowser(String browserName, String scenarioName) {
        boolean headless = Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        PropertiesUtil.getValue(ConfigProperties.HEADLESS)
                )
        );
        playwright.set(Playwright.create());
        browser.set(getBrowserType(browserName).launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(0))
        );

        logger.info("Launching {} browser", browserName);
        context.set(browser.get().newContext(getContextOptions(scenarioName)));
        context.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
        page.set(context.get().newPage());
    }

    private static Browser.NewContextOptions getContextOptions(String scenarioName) {
        return new Browser.NewContextOptions()
                .setRecordVideoDir(
                        Paths.get(FrameworkConstants.getVideoFolder())).setViewportSize(1920, 1080)
                .setIgnoreHTTPSErrors(true).setLocale("en-IN")
                .setTimezoneId("Asia/Kolkata").setColorScheme(ColorScheme.DARK)
                .setPermissions(List.of("clipboard-read", "camera", "microphone"))
                .setRecordHarPath(Paths.get(FrameworkConstants.getHarFilePath(scenarioName)))
                .setUserAgent("UAT Automation testing");
//                .setBaseURL(PropertiesUtil.getValue(ConfigProperties.BASEURL));
    }
    //setViewportSize - horizontal , vertical height
    //without setIgnoreHTTPError - Your connection is not private
    //setLocale - useful while validating date, currency , language


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
        close(page);
        close(context);
        close(browser);
        close(playwright);
    }

    //helper class
    private static <T extends AutoCloseable> void close(ThreadLocal<T> object) {
        try {
            if (object.get() != null)
                object.get().close(); // removes pw references
        } catch (Exception ignored) {
        }
        object.remove(); // removes threadlocal references
    }
}
//here page, browsercontext and browser --> all are not thread safe
