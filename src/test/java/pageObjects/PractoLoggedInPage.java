package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PractoLoggedInPage extends BasePage{
	

	public PractoLoggedInPage(WebDriver driver) {
		super(driver);
	}
	

	@FindBy(xpath = "//span[@class='up-triangle']//span[@class='user_info_top']")
	WebElement profileName;
	
	@FindBy(xpath="//span[@class='up-triangle']//span[@class='downarrow icon-ic_down_cheveron']")
	WebElement drop;
	
	@FindBy(xpath="//div[@class='u-d nav-dropdown text-left active-state']//a[@class='nav-interact logout-click'][normalize-space()='Logout']")
	WebElement logout;
	

	@FindBy(xpath="//span[@class='user_info_top']")
	WebElement userInfo;
	
	@FindBy(xpath="//div[@class='u-d nav-dropdown text-left active-state']//span[contains(text(),'My Tests')]")
	WebElement myTests;

	public WebElement fetchProfileElement() {
		return profileName;
	}
	
	public void logoutToHome() {
		drop.click();
		logout.click();
	}
	
	public void clickDrop() {
		drop.click();
	}
	public void clickMyTests() {
		myTests.click();
	}

	
}
