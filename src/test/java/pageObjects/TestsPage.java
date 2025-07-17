package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestsPage extends BasePage{

	public TestsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='Book tests']")
	WebElement bookTests;
	
	@FindBy(xpath="//li[@class='u-text--center']") 
	List<WebElement> cities;
	
	public void clickBookTests() {
		bookTests.click();
	}
	
	public int noOfCities() {
		return cities.size();
	}
	
	public List<WebElement> topCities() {
		return cities;
	}
	
}
