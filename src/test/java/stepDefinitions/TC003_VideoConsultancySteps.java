package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;

import pageObjects.HomePage;
import pageObjects.Instant_Video_Consultation;
import hooks.Hooks;

public class TC003_VideoConsultancySteps {
	@Given("User clicks Video Consultation Button")
	public void User_clicks_Video_Consultation_Button() {

	HomePage homepage = new HomePage(Hooks.driver);
	
	Hooks.logger.info("****Clicking Instant Video Consultation*****");
	homepage.clickVideoCosultBtn();
	}
	
	@Then("Scrolling is done and user gets all rates")
	public void Scrolling_is_done_and_user_gets_all_rates() throws InterruptedException {
		try {
		Instant_Video_Consultation video = new Instant_Video_Consultation(Hooks.driver);
		
		Hooks.logger.info("****Scrolling*****");
		video.scroll();
		
		Hooks.logger.info("****passing and getting specialities with price*****");
		video.putSpecialities(video.cards);
		
		Assert.assertTrue(true);
	}catch(Exception e) {
		Hooks.logger.error("Test Failed", e.getMessage(), e);
		Hooks.logger.debug("Debug logs....");
		Assert.fail();
	}
}
}
