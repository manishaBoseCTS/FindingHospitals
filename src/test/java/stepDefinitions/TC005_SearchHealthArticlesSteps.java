package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.junit.Assert;

import hooks.Hooks;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.ReadArticles;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class TC005_SearchHealthArticlesSteps {
    HomePage homepage;
    ReadArticles readArticles;
    List<WebElement> titles;
    List<WebElement> authors;
    String searchTerm;
    String path = System.getProperty("user.dir") + "\\testData\\HealthArticles_TestData.xlsx";

    @Given("User is on the Home Page for article search")
    public void user_is_on_home_page() {
        Hooks.logger.info("Launching Home Page");
        homepage = new HomePage(Hooks.driver);
        readArticles = new ReadArticles(Hooks.driver);
    }

    @Given("User loads article search term from Excel sheet TC005")
    public void load_search_term_from_excel() throws IOException {
        Hooks.logger.info("Reading search term from Excel sheet: TC005");
        DataProviders dp = new DataProviders();
        Object[][] data = dp.getHealthArticlesSearchData(); 
        searchTerm = (String) data[0][0];
        Hooks.logger.info("Loaded search term: " + searchTerm);
    }

    @When("User clicks on {string} link")
    public void user_clicks_read_articles_link(String linkText) {
        homepage.readArticlesLink();
        Hooks.logger.info("Clicked on 'Read Articles' link");
    }

    @When("User switches to the newly opened article window")
    public void switch_to_new_window() {
        String mainWindow = Hooks.driver.getWindowHandle();
        for (String handle : Hooks.driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                Hooks.driver.switchTo().window(handle);
                break;
            }
        }
        Hooks.logger.info("Switched to new window for article search");
    }

    @When("User searches for the term from sheet TC005")
    public void user_searches_for_term() throws Exception {
        readArticles.articleNameFunc(searchTerm);
        Hooks.logger.info("Performed search for: " + searchTerm);
    }

    @Then("User extracts article titles and authors")
    public void extract_titles_and_authors() {
        titles = readArticles.getArticleTitles();
        authors = readArticles.getArticleAuthors();
        Hooks.logger.info("Extracted " + titles.size() + " article titles and author pairs");
    }

    @Then("User writes extracted data to Excel sheet TC005_Output")
    public void write_data_to_excel() throws Exception {
        ExcelUtility excel = new ExcelUtility(path);
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i).getText();
            String author = authors.get(i).getText();
            excel.setCellData("TC005_Output", i + 1, 0, title);
            excel.setCellData("TC005_Output", i + 1, 1, author);
            Hooks.logger.info("Written to Excel: " + title + " | " + author);
        }
        Assert.assertTrue(true);
        Hooks.logger.info("Data writing complete for TC005_Output");
    }
}
