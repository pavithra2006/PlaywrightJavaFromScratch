package runners;

@CucumberOptions(
        features =
                "src/test/resources/features",

        glue = {
                "stepdefinitions",
                "hooks"
        },

        plugin = {
                "pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json"
        }
)
public class TestRunner
        extends AbstractTestNGCucumberTests {
}