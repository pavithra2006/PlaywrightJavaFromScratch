package page;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    private final String lblDashboard =
            "//h6[text()='Dashboard']";

    public HomePage(Page page){
        super(page); //gets parent class constructor - Page from BasePage
    }

    public boolean isDashboardDisplayed(){
        logger.info("Inside isDashboardDisplayed");
        return page.locator(lblDashboard)
                .isVisible();
    }
}
