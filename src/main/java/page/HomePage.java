package page;

import base.BasePage;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private static final Logger logger =
            LoggerFactory.getLogger(LoginPage.class);

    private final String lblDashboard =
            "//h6[text()='Dashboard']";

    public HomePage(Page page){
        super(page);
    }

    public boolean isDashboardDisplayed(){
        logger.info("Inside isDashboardDisplayed");
        return page.locator(lblDashboard)
                .isVisible();
    }
}
