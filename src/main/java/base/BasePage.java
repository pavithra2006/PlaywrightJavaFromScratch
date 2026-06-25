package base;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.LoginPage;

public class BasePage {
    private static final Logger logger =
            LoggerFactory.getLogger(LoginPage.class);
    protected Page page;

    public BasePage(Page page){
        this.page = page;
    }

    protected void click(String locator){
        page.locator(locator).click();
        logger.info("Clicked " + locator + " locator successfully");
    }

    protected void fill(String locator,String value){
        page.locator(locator).fill(value);
        logger.info("Performed fill action on: " + locator + ", with value: " + value);
    }

    protected String getText(String locator){
        return page.locator(locator).textContent();
    }
}