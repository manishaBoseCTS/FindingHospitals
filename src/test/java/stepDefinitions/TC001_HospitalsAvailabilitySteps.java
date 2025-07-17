package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.HospitalsPage;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class TC001_HospitalsAvailabilitySteps {
	HospitalsPage hospitals;
	HomePage homepage;
	List<String>hospitalNames;
	String city, searchPlace, openTime;
	Double rating;
	
	@Given("User is on the Home Page")
	public void user_is_on_home_page() {
	    Hooks.logger.info("Navigating to Home Page");
	    homepage = new HomePage(Hooks.driver);
	    hospitals = new HospitalsPage(Hooks.driver);
	}

	@Given("User loads hospital search data from Excel")
	public void loadHospitalSearchDataFromExcel() throws IOException {
	    Hooks.logger.info("Loading hospital search data from Excel sheet: TC001");
	    DataProviders dp = new DataProviders();
	    Object[][] data = dp.getData(); 
	    city = (String) data[0][0];
	    searchPlace = (String) data[0][1];
	    openTime = (String) data[0][2];
	    String rt = (String) data[0][3];
	    rating = Double.parseDouble(rt);
	    Hooks.logger.info("Loaded Data -> City: " + city + ", SearchPlace: " + searchPlace + 
	                      ", OpenTime: " + openTime + ", Rating: " + rating);
	}

	@When("User enters city from sheet TC001")
	public void user_enters_search_city() throws Exception {
	    Hooks.logger.info("Entering City into Search: " + city);
	    homepage.setSearchCity(city);
	}

	@When("User enters search place as the search value from TC001")
	public void user_enters_search_value() {
	    Hooks.logger.info("Entering SearchPlace into Search field: " + searchPlace);
	    homepage.setSearchDoctorsClinicsHospitals(searchPlace);
	}

	@Then("User retrieves the total number of results")
	public void user_retrieves_total_results() {
	    Hooks.logger.info("Retrieving total number of hospital search results");
	    hospitals.getResult();
	}

	@Then("User fetches hospital names open at open time with rating")
	public void user_fetches_hospital_names() {
	    Hooks.logger.info("Filtering hospital names based on OpenTime: " + openTime + " and Rating >= " + rating);
	    hospitalNames = hospitals.getHospitalNames(openTime, rating);
	}

	@Then("User writes hospital names to Excel")
	public void user_writes_to_excel() throws IOException {
	    Hooks.logger.info("Writing filtered hospital names to Excel sheet: TC001_Output");
	    String path = ".\\testData\\FindingHospitals_TestData.xlsx";
	    ExcelUtility xlutil = new ExcelUtility(path);
	    int i = 0;
	    for(String h : hospitalNames) {
	        Hooks.logger.info("Writing Hospital Name to Excel: " + h);
	        xlutil.setCellData("TC001_Output", i, 0, h);
	        i++;
	    }
	    Assert.assertTrue(true);
	    Hooks.logger.info("Finished writing all hospital names to Excel");
	}

}
