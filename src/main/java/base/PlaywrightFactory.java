package base;

import com.microsoft.playwright.*;
import enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.PropertiesUtil;

import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlaywrightFactory {

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>(); // cant be modified and used within class only
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initBrowser() {
        playwright.set(Playwright.create());
        browser.set(
                playwright.get().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(PropertiesUtil.getValue(ConfigProperties.HEADLESS)))));
        context.set(browser.get().newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos"))));
        context.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
        page.set(context.get().newPage());
    }

    //thread local variables are private final -so use getters
    public static Page getPage() {return page.get();}

    public static BrowserContext getContext() {return context.get(); }

    public static Browser getBrowser() {return browser.get();}

    public static Playwright getPlaywright() {return playwright.get();}

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
