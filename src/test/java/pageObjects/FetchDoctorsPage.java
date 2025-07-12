package pageObjects;

import java.time.Duration;
import java.util.List;
import utilities.WaitUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FetchDoctorsPage extends BasePage{
	
	public FetchDoctorsPage(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@FindBy(xpath="//span[@data-qa-id='doctor_gender_selected']//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right']")
	WebElement genderDrp;
	
	@FindBy(xpath="//span[normalize-space()='Male Doctor']")
	WebElement maleDrp;
	
	@FindBy(xpath="//span[@data-qa-id='years_of_experience_selected']//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right']")
	WebElement experienceDrp;
	
	@FindBy(xpath="//span[normalize-space()='5+ Years of experience']")
	WebElement exp5yrs;
	
	@FindBy(xpath="//span[@class='c-sort-dropdown__selected c-dropdown__selected']//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right']")
	WebElement sortDrp;
	
	@FindBy(xpath="//span[normalize-space()='Consultation Fee - Low to High']")
	WebElement feeLowToHigh;
	
	@FindBy(xpath="//*[@class='doctor-name']")
	List<WebElement> doctorsName;
	
	public void selectGender() {
		WaitUtils.waitForDuration(driver, 1);
		genderDrp.click();
		WaitUtils.waitForDuration(driver, 1);
		maleDrp.click();
	}
	
	public void selectExperience() {
		WaitUtils.waitForDuration(driver, 1);
		experienceDrp.click();
		WaitUtils.waitForDuration(driver, 1);
		exp5yrs.click();
	}
	
	public void sortFeesAscending() {
		WaitUtils.waitForDuration(driver, 1);
		sortDrp.click();
		WaitUtils.waitForDuration(driver, 1);
		feeLowToHigh.click();
	}
	
	public List<WebElement> fetchDoctorsName() {
		WaitUtils.waitForDuration(driver, 2);
		return doctorsName;
	}
}
