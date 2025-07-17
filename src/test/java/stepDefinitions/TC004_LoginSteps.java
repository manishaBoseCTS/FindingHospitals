package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.Login;
import pageObjects.HomePage;
import pageObjects.PractoLoggedInPage;
import hooks.Hooks;
import utilities.DataProviders;

import java.time.Duration;

public class TC004_LoginSteps {

    @Given("User performs login authentication using Excel data")
    public void user_performs_login_authentication_using_excel_data() {
        Hooks.logger.info("******Starting Login Authentication Test Case*******");

        try {
            // Use Object[][] and cast each row
            Object[][] loginData = new DataProviders().getLoginData();

            for (Object[] row : loginData) {
                String email = (String) row[0];
                String password = (String) row[1];
                String type = (String) row[2];

                Hooks.logger.info("*****Clicking Login on Home Page******");
                HomePage hp = new HomePage(Hooks.driver);
                hp.clickLogin();

                Hooks.logger.info("*****Entering username and password and clicking login******");
                Login login = new Login(Hooks.driver);
                login.loginWithCredentials(email, password);

                WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
                PractoLoggedInPage pli = new PractoLoggedInPage(Hooks.driver);

                if (type.equalsIgnoreCase("Valid")) {
                    wait.until(ExpectedConditions.visibilityOf(pli.fetchProfileElement()));
                    Hooks.logger.info("*****Checking if Profile Info is displayed after successful login******");
                    Assert.assertTrue(pli.fetchProfileElement().isDisplayed(), "Login failed for valid credentials.");
                    Hooks.logger.info("*****Logging out******");
                    pli.logoutToHome();
                } else {
                    Hooks.logger.info("*****Checking visibility of username error block upon unsuccessful login******");
                    wait.until(ExpectedConditions.visibilityOf(hp.fetchUsernameErrorBlock()));
                    Assert.assertTrue(hp.fetchUsernameErrorBlock().isDisplayed(), "Error block not displayed for invalid credentials.");
                }
            }

        } catch (Exception e) {
            Hooks.logger.error("Test Failed", e.getMessage(), e);
            Hooks.logger.debug("Debug logs....");
            Assert.fail();
        }

        Hooks.logger.info("****Finished Login Authentication TestCase*****");
    }
}
