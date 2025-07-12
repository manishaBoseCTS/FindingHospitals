
package testCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

import pageObjects.Login;
import pageObjects.HomePage;
import pageObjects.PractoLoggedInPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC004_LoginAuthentication extends BaseClass {


	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void loginTest(String email, String password, String type) {
		logger.info("******Starting Login Authentication Test Case*******");
		try {
		logger.info("*****Clicking Login on Home Page******");
		HomePage hp = new HomePage(driver);
        hp.clickLogin();
        
		logger.info("*****Entering username and password and clicking login******");
        Login login = new Login(driver);
        login.loginWithCredentials(email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PractoLoggedInPage pli = new PractoLoggedInPage(driver);

        if (type.equalsIgnoreCase("Valid")) {
            wait.until(ExpectedConditions.visibilityOf(pli.fetchProfileElement()));
    		logger.info("*****Checking if Profile Info is displayed after successful login******");
            Assert.assertTrue(pli.fetchProfileElement().isDisplayed(), "Login failed for valid credentials.");
    		logger.info("*****Logging out******");
            pli.logoutToHome();
        } else {
//    		logger.info("*****Checking invisibility of Profile Info upon unsuccessful login******");
//            boolean loginFailed = wait.until(ExpectedConditions.invisibilityOf(pli.fetchProfileElement()));
//            Assert.assertTrue(loginFailed, "Login succeeded for invalid credentials.");
        	logger.info("*****Checking visibility of username error block upon unsuccessful login******");
        	wait.until(ExpectedConditions.visibilityOf(hp.fetchUsernameErrorBlock()));
        	Assert.assertTrue(hp.fetchUsernameErrorBlock().isDisplayed(), "Error block not displayed for invalid credentials.");

        }
   
	} catch(Exception e) {
		logger.error("Test Failed", e.getMessage(), e);
		logger.debug("Debug logs....");
		Assert.fail();
	}
	logger.info("****Finished Login Authentication TestCase*****");
}
}

