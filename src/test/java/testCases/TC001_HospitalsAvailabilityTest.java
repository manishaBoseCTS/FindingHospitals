package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.HospitalsPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtility;

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
			List<String> hospitalNames = hospitals.getHospitalNames(openTime, Double.parseDouble(rating));
			
			logger.info("****Writing Name of Hospitals to Excel****");
			String path = ".\\testData\\FindingHospitals_TestData.xlsx";
			ExcelUtility xlutil = new ExcelUtility(path);
			int i = 0;
			for(String h: hospitalNames) {
				xlutil.setCellData("TC001_Output", i, 0, h);
				i++;
			}
			Assert.assertTrue(true);
		} catch(Exception e) {
			logger.error("Test Failed", e.getMessage(), e);
			logger.debug("Debug logs....");
			Assert.fail();
		}
		logger.info("****Finished Searching Hospitals TestCase*****");
	}
}
