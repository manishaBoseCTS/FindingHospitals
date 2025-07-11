package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.TestsPage;
import testBase.BaseClass;

public class TC008_TopCity_TestPageTest extends BaseClass{

	@Test
	
	public void getTopCities() {

		logger.info("****Starting TestCase*****");
		try {
			HomePage homepage = new HomePage(driver); 
			TestsPage testPage = new TestsPage(driver);
			
			logger.info("****Clicking userInfo*****");
			homepage.clickUserInfo();
			
			logger.info("****Clicking My Tests*****");
			homepage.clickMyTests();
			
			logger.info("****Clicking Book Tests*****");
			testPage.clickBookTests();
			
			logger.info("****Number of Top cities*****");
			testPage.noOfCities();
			
			logger.info("****List Of Cities*****");
			List<WebElement> topCities=testPage.topCities();
			for(WebElement topCity : topCities ) {
				System.out.println(topCity.getText());
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
