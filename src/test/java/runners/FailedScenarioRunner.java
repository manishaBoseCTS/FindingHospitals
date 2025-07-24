package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)

@CucumberOptions(
    features = "@target/failedrerun.txt", 
    glue = {"stepDefinitions", "hooks"},  
    plugin = {
        "pretty",
        "html:target/rerun-reports.html",
        "json:target/rerun-report.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Allure integration
    },
    monochrome = true
)
public class FailedScenarioRunner extends AbstractTestNGCucumberTests {
	
}
