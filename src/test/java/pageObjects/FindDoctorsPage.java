package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.WaitUtils;

public class FindDoctorsPage extends BasePage{
	
	public FindDoctorsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search location']")
	WebElement location;
	
	@FindBy(xpath="//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
	WebElement doctorType;
	
	public void selectLocation(String locationName) {
		location.clear();
		location.sendKeys(locationName);
	}
	
	public void selectDoctorType(String doctorTypeName) {
		doctorType.sendKeys(doctorTypeName);
		WaitUtils.waitForDuration(driver, 3);
        doctorType.sendKeys(Keys.ENTER);
	}

}
