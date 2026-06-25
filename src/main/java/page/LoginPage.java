package page;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPage extends BasePage {

    private static final Logger logger =
                LoggerFactory.getLogger(LoginPage.class);

    private final String txtUsername =
            "//input[@name='username']";

    private final String txtPassword =
            "//input[@name='password']";

    private final String btnLogin =
            "//button[@type='submit']";


    public LoginPage(Page page) {
        super(page);
    }

    public void login(String user,
                      String password){

        fill(txtUsername,user);
        fill(txtPassword,password);
        WaitUtils.waitForSelector(page,
                btnLogin);
        click(btnLogin);
    }
}
