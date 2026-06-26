package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage extends BasePage {

    private final String lblDashboard =
            "//h6[text()='Dashboard']";

    public HomePage(Page page){
        super(page); //gets parent class constructor - Page from BasePage
    }

    public boolean isDashboardDisplayed(){
//        page.pause();
       page.locator(lblDashboard).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        boolean dashboardHeader=page.locator(lblDashboard)
                .isVisible();
        logger.info("Is dashboard header displayed: " + dashboardHeader);
        return dashboardHeader;
    }
}
