package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/failed_scenarios.txt",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty"
        }
)
public class FailureRunner extends BaseRunner {
}