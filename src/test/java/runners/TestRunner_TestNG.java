package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
   features = "src/test/resources/features",     // your feature files
   glue = "stepdefinitions",                     // your step definitions
   plugin = {
       "pretty",
       "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"   // This is required
   }
)
public class TestRunner_TestNG extends AbstractTestNGCucumberTests {
}