package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.WaitUtils;

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
	
	@FindBy(xpath="//a[normalize-space()='Login / Signup']")
	WebElement login;
	
	@FindBy(id="usernameErrorBlock")
	WebElement errorBlock;
	
	@FindBy(xpath="//a[@aria-label='Instant Video Consultation']")
	WebElement videoCosultBtn;

	@FindBy(linkText="Find Doctors")
	WebElement doctorsLink;
	
	@FindBy(xpath="//span[normalize-space()='Read health articles']")
	WebElement readArticles;
	
	@FindBy(xpath="//span[@class='nav-interact']")
	WebElement forCorporateClick;
	
	@FindBy(xpath="//a[normalize-space()='Health & Wellness Plans']")
	WebElement health;

	
	public void setSearchCity(String searchItem) throws Exception{
		searchCity.click();
		cross.click();
		searchCity.sendKeys(searchItem);
		WaitUtils.waitForDuration(driver, 5); //to delete
		
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
	
	public WebElement fetchUsernameErrorBlock() {
	    return errorBlock;
	}
	
	public void setSearchDoctorsClinicsHospitals(String searchItem) {
		searchHospitals.sendKeys(searchItem);
		driver.findElement(By.xpath("//div[normalize-space()='"+searchItem+"']")).click();
	}
	

	public void clickLogin() {
		login.click();
	}
	

	public void clickVideoCosultBtn() {
		videoCosultBtn.click();
	}
	


	public void clickFindDoctors() {
		doctorsLink.click();
	}
	public void readArticlesLink() {
		readArticles.click();
	}
	
	public void forCorporateClickFunc() {
		forCorporateClick.click();
	}
	public void forHealthFunc() {
		health.click();
	}

}
