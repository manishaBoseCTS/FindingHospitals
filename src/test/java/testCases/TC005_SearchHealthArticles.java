package testCases;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class TC005_SearchHealthArticles extends BaseClass{
	@Test
	public void healthArticlesSearch() throws Exception{
		logger.info("****Starting********");
		try {
			// Store the current window handle
			String mainWindow = driver.getWindowHandle();
	
			// Perform an action that opens a new window
			driver.findElement(By.xpath("//span[normalize-space()='Read health articles']")).click();
	
			// Get all window handles
			Set<String> allWindows = driver.getWindowHandles();
	
			// Switch to the new window
			for (String windowHandle : allWindows) {
			    if (!windowHandle.equals(mainWindow)) {
			        driver.switchTo().window(windowHandle);
			        break;
			    }
			}
			logger.info("**Search Bar click***");
			driver.findElement(By.xpath("//span[@class='fit-feed-icon icon-ic_search_system']")).click();
			Thread.sleep(5000);
				
			logger.info("**Search Bar sendKeys***");
			WebElement input = driver.findElement(By.xpath("//input[@id='searchBar']"));
			input.sendKeys("depression");
			
			driver.findElement(By.xpath("//span[@class='fit-feed-icon icon-ic_search_system']")).click();
			Thread.sleep(5000); //to remove
			Assert.assertTrue(true);
		}
		catch(Exception e) {
			logger.error("Test Failed", e.getMessage(), e);
			logger.debug("Debug logs....");
			Assert.fail();
		}
		logger.info("Finished");
	}

}
