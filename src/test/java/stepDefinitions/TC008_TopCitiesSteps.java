package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.PractoLoggedInPage;
import pageObjects.TestsPage;
import hooks.Hooks;
import utilities.ExcelUtility;
import utilities.WaitUtils;

import java.time.Duration;
import java.util.List;

import org.testng.Assert;

public class TC008_TopCitiesSteps {

    List<WebElement> topCities;

    @Given("User logs into the application with valid credentials")
    public void user_logs_into_the_application_with_valid_credentials() {
        try {
            Hooks.logger.info("Starting login process...");
            HomePage homepage = new HomePage(Hooks.driver);
            homepage.clickLogin();

            Login login = new Login(Hooks.driver);
            login.loginWithCredentials("chatterjeenaitik0@gmail.com", "Practo2025@#$");

            PractoLoggedInPage afterLogin = new PractoLoggedInPage(Hooks.driver);
            WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(afterLogin.fetchProfileElement()));

            Hooks.logger.info("Login successful.");
        } catch (Exception e) {
            Hooks.logger.error("Login failed", e);
            Assert.fail("Login failed due to exception: " + e.getMessage());
        }
    }

    @When("User navigates to Book Tests and fetches TopCities")
    public void user_navigates_to_book_tests_and_fetches_top_cities() {
        try {
            PractoLoggedInPage afterLogin = new PractoLoggedInPage(Hooks.driver);
            afterLogin.clickDrop();
            Hooks.logger.info("Clicked user dropdown.");

            afterLogin.clickMyTests();
            Hooks.logger.info("Navigated to My Tests.");

            WaitUtils.waitForDuration(Hooks.driver, 2);

            TestsPage testPage = new TestsPage(Hooks.driver);
            testPage.clickBookTests();
            Hooks.logger.info("Clicked Book Tests.");

            topCities = testPage.topCities();
            Hooks.logger.info("Fetched {} TopCities.", topCities.size());

        } catch (Exception e) {
            Hooks.logger.error("Failed to fetch TopCities", e);
            Assert.fail("Fetching TopCities failed due to exception: " + e.getMessage());
        }
    }

    @Then("TopCities should be written to Excel sheet {string}")
    public void topCities_should_be_written_to_excel_sheet(String sheetName) {
        try {
            String path = System.getProperty("user.dir") + "\\testData\\TopCities.xlsx";
            ExcelUtility excel = new ExcelUtility(path);

            int row = 1;
            for (WebElement topCity : topCities) {
                String cityName = topCity.getText();
                excel.setCellData(sheetName, row, 0, cityName);
                Hooks.logger.info("Written TopCity to Excel: {}", cityName);
                row++;
            }

            Assert.assertTrue(topCities.size() > 0, "No TopCities were written to Excel.");
            Hooks.logger.info("All TopCities written to Excel successfully.");

        } catch (Exception e) {
            Hooks.logger.error("Error writing TopCities to Excel", e);
            Assert.fail("Excel writing failed due to exception: " + e.getMessage());
        }
    }
}
