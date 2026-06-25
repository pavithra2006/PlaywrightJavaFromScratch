package hooks;

import base.PlaywrightFactory;

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