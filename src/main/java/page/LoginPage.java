package page;

public class LoginPage extends BasePage {

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
        click(btnLogin);
    }
}
