package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;

import pageObjects.FetchDoctorsPage;
import pageObjects.FindDoctorsPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_NearbyDoctors extends BaseClass{
	
	@Test(dataProvider = "DoctorsSearchData", dataProviderClass = DataProviders.class)
	public void findDoctorsTest(String location, String doctorType) {
	logger.info("****Starting Search Nearby Doctors TestCase*****");
	try {
	HomePage hm = new HomePage(driver);
	hm.clickFindDoctors();
	FindDoctorsPage fid = new FindDoctorsPage(driver);
	
	logger.info("****Selecting Location*****");
	fid.selectLocation(location);
	logger.info("****Selecting Doctor Type*****");
	fid.selectDoctorType(doctorType);
	
	FetchDoctorsPage fed = new FetchDoctorsPage(driver);
	logger.info("****Selecting Gender*****");
	fed.selectGender();
	logger.info("****Selecting Experience*****");
	fed.selectExperience();
	logger.info("****Sorting names based on fees*****");
	fed.sortFeesAscending();
	logger.info("****Storing doctor names in a list*****");
	List<WebElement> doctorsList = fed.fetchDoctorsName();
	for(WebElement name  : doctorsList) {
		System.out.println(name.getText());
	}
	
	}catch(Exception e) {
		logger.error("Test Failed", e.getMessage(), e);
		logger.debug("Debug logs....");
		Assert.fail();
	}
	logger.info("****Finished Searching Doctors TestCase*****");
	}
}
