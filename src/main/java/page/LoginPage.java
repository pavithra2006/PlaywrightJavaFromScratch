package page;

import com.microsoft.playwright.Page;
import report.ExtentLogger;
import report.ExtentSpark;
import utils.WaitUtils;


public class LoginPage extends BasePage {
    private final String txtUsername = "//input[@name='username']";
    private final String txtPassword = "//input[@name='password']";
    private final String btnLogin = "//button[@type='submit']";
    private final String picProfile = "//header[@class='oxd-topbar']//img[@alt='profile picture']";
    private final String btnLogout = "//a[text()='Logout']";
    private final String loginPgHeader = "//div[@class='orangehrm-login-branding']";
    private final String toastMsg = "//p[contains(@class,'alert-content')]";

    public LoginPage(Page page) {
        super(page);
    }

    public void login(String user,
                      String password) {

        fill(txtUsername, user);
        fill(txtPassword, password);
        WaitUtils.waitForSelector(page,
                btnLogin);
        click(btnLogin);
        ExtentLogger.info("Login successful");
    }

    public void logout() {
        click(picProfile);
        WaitUtils.waitForSelector(page, btnLogout);
        click(btnLogout);
        ExtentLogger.info("Logout successful");
    }

    public boolean loginPgHeaderDisplayed() {
        WaitUtils.waitForSelector(page, loginPgHeader);
        return isDisplayed(loginPgHeader, true);
    }

    public String verifyToastMessage(){
        isDisplayed(toastMsg, true);
        String toastMsgContent = getText(toastMsg);
        ExtentLogger.info("Toast message content: " + toastMsgContent);
        return toastMsgContent;
    }
}
