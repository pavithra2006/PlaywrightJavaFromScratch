package page;

import base.BasePage;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    private final String lblDashboard =
            "//h6[text()='Dashboard']";

    public HomePage(Page page){
        super(page);
    }

    public boolean isDashboardDisplayed(){

        return page.locator(lblDashboard)
                .isVisible();
    }
}
