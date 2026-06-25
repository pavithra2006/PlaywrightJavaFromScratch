package page;

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
