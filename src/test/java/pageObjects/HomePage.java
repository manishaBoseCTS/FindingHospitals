package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//i[@class='icon-ic_cross_solid']")
	WebElement cross;
	
	@FindBy(xpath="//input[@placeholder='Search location']")
	WebElement searchCity;
	
	@FindBy(xpath="//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
	WebElement searchHospitals;
	
	public void setSearchCity(String searchItem) throws Exception{
		searchCity.click();
		cross.click();
		searchCity.sendKeys(searchItem);
		Thread.sleep(5000); //to delete
		
		//String location = driver.findElement(By.xpath("//input[@placeholder='Search location']")).getAttribute("value");
		//System.out.println("Search location value: " + location);
		
		List<WebElement> exactMatch = driver.findElements(By.xpath("//div[normalize-space()='" + searchItem + "']"));

		if (!exactMatch.isEmpty()) {
		    exactMatch.get(0).click();
		} else {
		    List<WebElement> fallbackMatch = driver.findElements(By.xpath("//div[contains(text(),'Search in entire " + searchItem + "')]"));
		    fallbackMatch.get(0).click();
		}

	}
	
	public void setSearchDoctorsClinicsHospitals(String searchItem) {
		searchHospitals.sendKeys(searchItem);
		driver.findElement(By.xpath("//div[normalize-space()='"+searchItem+"']")).click();
	}
}
