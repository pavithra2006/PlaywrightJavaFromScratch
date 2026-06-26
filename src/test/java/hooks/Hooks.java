package hooks;

import base.PlaywrightFactory;
import com.microsoft.playwright.Page;
import context.TestContext;
import enums.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.PropertiesUtil;

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

        page.navigate(PropertiesUtil.getValue(ConfigProperties.BASEURL));
    }

    @After
    public void tearDown() {

        PlaywrightFactory.getPage()
                .context()
                .browser()
                .close();
    }
}