package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import report.ExtentSpark;

//testng runner
@CucumberOptions(
        features =
                "src/test/resources/feature",
        glue = {
                "stepdefinitions",
                "hooks"
        },
        plugin = {
                "pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "rerun:target/failed_scenarios.txt"

        }
)
public class TestRunner
        extends BaseRunner {

}

