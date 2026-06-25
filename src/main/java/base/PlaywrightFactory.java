package base;

public class PlaywrightFactory {

    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initBrowser() {

        Playwright playwright = Playwright.create();

        Browser browser =
                playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false));

        BrowserContext context =
                browser.newContext();

        page.set(context.newPage());
    }

    public static Page getPage() {
        return page.get();
    }
}