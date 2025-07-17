package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.PractoLoggedInPage;
import pageObjects.TestsPage;
import testBase.BaseClass;
import utilities.ExcelUtility;
import utilities.WaitUtils;

public class TC008_TopCity_TestPageTest extends BaseClass{

	@Test
	
	public void getTopCities() {

		logger.info("****Starting TestCase*****");
		try { 
			
			HomePage homepage = new HomePage(driver);
			homepage.clickLogin();
			
			Login login = new Login(driver);
			login.loginWithCredentials("chatterjeenaitik0@gmail.com","Practo2025@#$");
			
			
			PractoLoggedInPage afterLogin = new PractoLoggedInPage(driver); 
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(afterLogin.fetchProfileElement()));
			
			logger.info("****Clicking userInfo*****");
			afterLogin.clickDrop();
			
			logger.info("****Clicking My Tests*****");
			afterLogin.clickMyTests();
			
			WaitUtils.waitForDuration(driver, 2);
			TestsPage testPage = new TestsPage(driver);
			
			logger.info("****Clicking Book Tests*****");
			testPage.clickBookTests();
			
			logger.info("****Number of Top cities*****");
			testPage.noOfCities();
			
			logger.info("****List Of Cities*****");
			int r = 1;
			List<WebElement> topCities=testPage.topCities();
			for(WebElement topCity : topCities ) {
				System.out.println(topCity.getText());
				String path = System.getProperty("user.dir")+"\\testData\\TopCities.xlsx";
				ExcelUtility eu = new ExcelUtility(path);
				eu.setCellData("TC008_Output", r, 0, topCity.getText());
				r++;
			}
			Assert.assertTrue(true);
			
			
		} catch(Exception e) {
			logger.error("Test Failed", e.getMessage(), e);
			logger.debug("Debug logs....");
			Assert.fail();
		}
		logger.info("****Finished TestCase*****");
	
	}
}