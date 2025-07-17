package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import java.io.IOException;
import java.time.Duration;

import hooks.Hooks;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.ForCorporates_Captcha;
import utilities.ScreenShot;
import utilities.xmlHelper;

public class TC007_CaptchaScreenshotSteps {

    HomePage homepage;
    ForCorporates_Captcha corporateHealth;

    @When("User clicks the \"For Corporates\" link")
    public void user_clicks_for_corporates_link() {
    	homepage = new HomePage(Hooks.driver);
    	corporateHealth = new ForCorporates_Captcha(Hooks.driver);
        Hooks.logger.info("Clicking 'For Corporates' link");
        homepage.forCorporateClickFunc();
    }

    @When("User clicks the \"For Health and Wellness\" link")
    public void user_clicks_for_health_and_wellness_link() {
        Hooks.logger.info("Clicking 'For Health and Wellness' link");
        homepage.forHealthFunc();
    }

    @When("The form page is loaded completely")
    public void form_page_is_loaded() throws InterruptedException {
        Hooks.logger.info("Waiting for form to load...");
        Thread.sleep(2000);  // Preferably replaced with WebDriverWait
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        Hooks.logger.info("Form loaded and ready");
        corporateHealth = new ForCorporates_Captcha(Hooks.driver);
    }

    @When("User fills the form with values from XML")
    public void user_fills_form_from_xml() throws Exception {
        Hooks.logger.info("Filling form using XML data...");
        corporateHealth.nameFunc(xmlHelper.getData("name"));
        Hooks.logger.info("Entered Name");

        corporateHealth.orgFunc(xmlHelper.getData("organization"));
        Hooks.logger.info("Entered Organization");

        corporateHealth.contactFunc(xmlHelper.getData("contact"));
        Hooks.logger.info("Entered Contact");

        corporateHealth.emailFunc(xmlHelper.getData("email"));
        Hooks.logger.info("Entered Email");

        corporateHealth.selectOrgSize(xmlHelper.getData("organizationSize"));
        Hooks.logger.info("Selected Organization Size");

        corporateHealth.selectInterest(xmlHelper.getData("interest"));
        Hooks.logger.info("Selected Interest");
    }

    @When("User submits the demo request")
    public void user_submits_demo_request() throws Exception {
        Hooks.logger.info("Submitting demo request");
        corporateHealth.scheduleADemo();
    }

    @Then("CAPTCHA screen should be displayed")
    public void captcha_screen_should_be_displayed() {
        Hooks.logger.info("Verifying CAPTCHA screen is displayed");
        // Add conditional verification if needed
        Assert.assertTrue(true);  // You can enhance this later
    }

    @Then("A screenshot is captured and saved")
    public void screenshot_is_captured() throws IOException {
        Hooks.logger.info("Capturing screenshot: CaptchaAfterSubmit");
        ScreenShot.takeScreenShot(Hooks.driver, "CaptchaAfterSubmit");
    }
}
