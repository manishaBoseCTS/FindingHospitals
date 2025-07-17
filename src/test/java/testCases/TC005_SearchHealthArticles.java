package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ReadArticles;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class TC005_SearchHealthArticles extends BaseClass {

    @Test(dataProvider = "HealthArticleSearchData", dataProviderClass = DataProviders.class)
    public void healthArticlesSearch(String searchTerm) throws Exception {
        logger.info("**** Starting TC005_SearchHealthArticles Test ****");

        try {
            HomePage homepage = new HomePage(driver);
            homepage.readArticlesLink();
            logger.info("Clicked on 'Read Articles' link");

            // Switch to the new window
            String mainWindow = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(mainWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            ReadArticles rd = new ReadArticles(driver);
            rd.articleNameFunc(searchTerm);
            logger.info("Searched for: " + searchTerm);

            List<WebElement> titles = rd.getArticleTitles();
            List<WebElement> authors = rd.getArticleAuthors();

            String path = System.getProperty("user.dir") + "\\testData\\HealthArticles_TestData.xlsx";
            ExcelUtility excel = new ExcelUtility(path);

            for (int i = 0; i < titles.size(); i++) {
                excel.setCellData("TC005_Output", i + 1, 0, titles.get(i).getText());
                excel.setCellData("TC005_Output", i + 1, 1, authors.get(i).getText());
                logger.info("Saved: " + titles.get(i).getText() + " | " + authors.get(i).getText());
            }

            logger.info("Data written successfully to Excel");
            Assert.assertTrue(true);

        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage(), e);
            Assert.fail();
        }

        logger.info("**** Finished TC005_SearchHealthArticles Test ****");
    }
}


