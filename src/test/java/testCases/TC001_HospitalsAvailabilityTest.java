package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.HospitalsPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC001_HospitalsAvailabilityTest extends BaseClass{
	@Test (dataProvider = "FindingHospitalsData", dataProviderClass = DataProviders.class)
	public void hospitalsSearch(String city, String searchPlace, String openTime, String rating){
		logger.info("****Starting TestCase*****");
		try {
			HomePage homepage = new HomePage(driver); 
			HospitalsPage hospitals = new HospitalsPage(driver);
			
			logger.info("****Entering Search City*****");
			homepage.setSearchCity(city);
			
			logger.info("****Entering Search Value*****");
			homepage.setSearchDoctorsClinicsHospitals(searchPlace);
			
			logger.info("****Getting Total No. of Results*****");
			hospitals.getResult();
			
			logger.info("****Getting Name of Hospitals based on criteria*****");
			hospitals.getHospitalNames(openTime, Double.parseDouble(rating));
			
			Assert.assertTrue(true);
		} catch(Exception e) {
			logger.error("Test Failed", e.getMessage(), e);
			logger.debug("Debug logs....");
			Assert.fail();
		}
		logger.info("****Finished Searching Hospitals TestCase*****");
	}
}
