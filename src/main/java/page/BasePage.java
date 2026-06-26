package page;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    protected final Logger logger =
            LoggerFactory.getLogger(getClass());
    //protected because can be used in child classes - final - so value cant be modified

    protected Page page;

    public BasePage(Page page){
        this.page = page; // constructor to initialize page and avoid null pointer exception
    }

    protected void click(String locator){
        page.locator(locator).click();
        logger.info("Clicked {} locator successfully", locator);
    }

    protected void fill(String locator,String value){
        page.locator(locator).fill(value);
        logger.info("Performed fill action on: {}, with value: {}", locator, value);
    }

    protected String getText(String locator){
        String text = page.locator(locator).textContent();
        logger.info("Text content on locator: {}, with value: {} " , locator,text);
        return text;
    }
}