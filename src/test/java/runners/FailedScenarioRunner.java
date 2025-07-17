package runners;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
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
public class FailedScenarioRunner {}
