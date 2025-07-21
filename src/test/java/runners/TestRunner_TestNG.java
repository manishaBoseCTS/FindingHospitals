package runners;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.AllureReportOpener;

@CucumberOptions(
		features = "src/test/java/features",
		glue = {"stepDefinitions", "hooks"},
		plugin = {"pretty","html:target/cucumber-reports.html",
				"json:target/cucumber-reports/cucumber-report2.json",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},				 
		monochrome = true
		)
 
public class TestRunner_TestNG extends AbstractTestNGCucumberTests{
	
	@AfterSuite
	public void afterSuite() {
		AllureReportOpener.openAllureReport();
	}
	
}