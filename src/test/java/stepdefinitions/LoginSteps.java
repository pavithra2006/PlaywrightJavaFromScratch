package stepdefinitions;

import base.PlaywrightFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;
import page.HomePage;
import page.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void initialize() {

        loginPage =
                new LoginPage(PlaywrightFactory.getPage());

        homePage =
                new HomePage(PlaywrightFactory.getPage());
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String user, String password){
        loginPage.login(user, password);
    }

    @Then("Dashboard should be displayed")
    public void dashboard_should_be_displayed(){

        Assert.assertTrue(
                homePage.isDashboardDisplayed());
    }
}