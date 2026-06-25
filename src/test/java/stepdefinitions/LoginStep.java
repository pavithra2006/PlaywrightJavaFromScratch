package stepdefinitions;

public class LoginSteps {

    LoginPage loginPage =
            new LoginPage(
                    PlaywrightFactory.getPage());

    HomePage homePage =
            new HomePage(
                    PlaywrightFactory.getPage());

    @When("User enters username {string}")
    public void enterUsername(String user){

        loginPage.enterUsername(user);
    }

    @When("User enters password {string}")
    public void enterPassword(String pass){

        loginPage.enterPassword(pass);
    }

    @When("User clicks login")
    public void clickLogin(){

        loginPage.clickLogin();
    }

    @Then("Dashboard should be displayed")
    public void verifyDashboard(){

        Assert.assertTrue(
                homePage.isDashboardDisplayed());
    }
}