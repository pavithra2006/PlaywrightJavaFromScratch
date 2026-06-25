package hooks;

import base.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setup(){

        PlaywrightFactory.initBrowser();

        PlaywrightFactory.getPage()
                .navigate(
                        ConfigReader.get("baseUrl"));
    }

    @After
    public void tearDown(){

        PlaywrightFactory
                .getPage()
                .context()
                .browser()
                .close();
    }
}