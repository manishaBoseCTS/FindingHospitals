package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
//@CucumberOptions(
//    features = "src/test/java/features",
//    glue = {"stepDefinitions", "hooks"},
//    plugin = {
//        "pretty",
//        "html:target/cucumber-reports.html",
//        "json:target/cucumber.json",
//        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
//    },
//    monochrome = true
//)
@CucumberOptions(
	    features = "src/test/java/features",
	    glue = {"stepDefinitions", "hooks"},
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports.html",
	        "json:target/cucumber.json",
	        "rerun:target/failedrerun.txt",  
	        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
	    },
	    monochrome = true
	)
	
public class JUnitRunner {
}
