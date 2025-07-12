package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Instant_Video_Consultation;
import testBase.BaseClass;

public class TC003_VideoConsultancy extends BaseClass{

	@Test
	
	public void getTopCities() {

		logger.info("****Starting TestCase*****");
		try { 
			
			HomePage homepage = new HomePage(driver);
			
			logger.info("****Clicking Instant Video Consultation*****");
			homepage.clickVideoCosultBtn();
			
			
			Instant_Video_Consultation video = new Instant_Video_Consultation(driver);
			
			logger.info("****Scrolling*****");
			video.scroll();
			
			logger.info("****passing and getting specialities with price*****");
			video.putSpecialities(video.cards);
			
			Assert.assertTrue(true);
			
			
		} catch(Exception e) {
			logger.error("Test Failed", e.getMessage(), e);
			logger.debug("Debug logs....");
			Assert.fail();
		}
		logger.info("****Finished TestCase*****");
	
	}
}

