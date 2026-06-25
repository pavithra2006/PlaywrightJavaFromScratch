package hooks;

import base.PlaywrightFactory;
import com.microsoft.playwright.Page;
import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;

public class Hooks {

    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setup() {

        PlaywrightFactory.initBrowser();

        Page page = PlaywrightFactory.getPage();

        testContext.setPage(page);

        page.navigate(ConfigReader.get("baseUrl"));
    }

    @After
    public void tearDown() {

        PlaywrightFactory.getPage()
                .context()
                .browser()
                .close();
    }
}