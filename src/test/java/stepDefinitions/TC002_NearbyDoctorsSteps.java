package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import pageObjects.FetchDoctorsPage;
import pageObjects.FindDoctorsPage;
import pageObjects.HomePage;
import hooks.Hooks;
import utilities.ExcelUtility;

import java.util.ArrayList;
import java.util.List;

public class TC002_NearbyDoctorsSteps {

    List<String> doctorNames = new ArrayList<>();
    String location;
    String doctorType;

    @Given("User navigates to the application")
    public void user_navigates_to_application() {
        Hooks.logger.info("Navigating to application URL...");
        Hooks.driver.get(Hooks.p.getProperty("appURL"));
        Hooks.logger.info("User is on the home page.");
    }

    @When("User searches for doctors using data from sheet {string}")
    public void user_searches_for_doctors_using_data_from_sheet(String sheetName) {
        try {
            String path = System.getProperty("user.dir") + "\\testData\\NearbyDoctors_TestData.xlsx";
            ExcelUtility excel = new ExcelUtility(path);

            int totalRows = excel.getRowCount(sheetName);
            Hooks.logger.info("Total rows in sheet '{}': {}", sheetName, totalRows);

            for (int i = 1; i <= totalRows; i++) {
                location = excel.getCellData(sheetName, i, 0);
                doctorType = excel.getCellData(sheetName, i, 1);

                Hooks.logger.info("Searching for doctors in location: '{}' with type: '{}'", location, doctorType);

                performDoctorSearch(location, doctorType);
            }

        } catch (Exception e) {
            Hooks.logger.error("Error while reading Excel or performing search", e);
            throw new RuntimeException("Search failed", e);
        }
    }

    @Then("Doctor names should be saved to sheet {string}")
    public void doctor_names_should_be_saved_to_sheet(String outputSheet) {
        try {
            String path = System.getProperty("user.dir") + "\\testData\\NearbyDoctors_TestData.xlsx";
            ExcelUtility excel = new ExcelUtility(path);

            int row = 1;
            for (String name : doctorNames) {
                excel.setCellData(outputSheet, row, 0, name);
                Hooks.logger.info("Saved doctor name to Excel: {}", name);
                row++;
            }

            Hooks.logger.info("All doctor names saved to sheet '{}'", outputSheet);

        } catch (Exception e) {
            Hooks.logger.error("Error writing doctor names to Excel", e);
            throw new RuntimeException("Excel writing failed", e);
        }
    }

    // Helper method to perform search
    private void performDoctorSearch(String location, String doctorType) {
        HomePage homePage = new HomePage(Hooks.driver);
        homePage.clickFindDoctors();

        FindDoctorsPage findDoctorsPage = new FindDoctorsPage(Hooks.driver);
        findDoctorsPage.selectLocation(location);
        findDoctorsPage.selectDoctorType(doctorType);

        FetchDoctorsPage fetchDoctorsPage = new FetchDoctorsPage(Hooks.driver);
        fetchDoctorsPage.selectGender();
        fetchDoctorsPage.selectExperience();
        fetchDoctorsPage.sortFeesAscending();

        List<WebElement> doctorsList = fetchDoctorsPage.fetchDoctorsName();
        Hooks.logger.info("Doctors found: {}", doctorsList.size());

        if (doctorsList.isEmpty()) {
            Hooks.logger.warn("No doctors found for location '{}' and type '{}'", location, doctorType);
        }

        for (WebElement doctor : doctorsList) {
            String name = doctor.getText();
            doctorNames.add(name);
            Hooks.logger.debug("Doctor name added: {}", name);
        }
    }
}
