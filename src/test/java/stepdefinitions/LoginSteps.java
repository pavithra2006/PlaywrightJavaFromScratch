package stepdefinitions;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.HomePage;
import page.LoginPage;
import report.ExtentLogger;

public class LoginSteps {

    private final LoginPage loginPage;
    private final HomePage homePage;

    public LoginSteps(TestContext context) {
        loginPage = new LoginPage(context.getPage());
        homePage = new HomePage(context.getPage());
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String user, String password) {
        ExtentLogger.info("Test Data : username " + user +" and password " + password );
        Assert.assertTrue(loginPage.loginPgHeaderDisplayed());
        loginPage.login(user, password);
    }

    @Then("Dashboard should be displayed")
    public void dashboard_should_be_displayed() {
        Assert.assertTrue(homePage.isDashboardDisplayed());
    }

    @And("Verify logout is successful")
    public void verify_logout_is_successfull() {
        loginPage.logout();
        Assert.assertTrue(loginPage.loginPgHeaderDisplayed());
    }

    @Then("Verify login fails with toast message {string}")
    public void verify_login_fails_with_toast_message(String expectedToastMessage){
        Assert.assertEquals(loginPage.verifyToastMessage(), expectedToastMessage);
    }
}