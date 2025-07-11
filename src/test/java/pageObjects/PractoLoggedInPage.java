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
	
	@FindBy(xpath="/html/body/div[1]/div[2]/div/div[3]/div[4]")
	WebElement drop;
	
	@FindBy(xpath="//div[@class='u-d nav-dropdown text-left active-state']//a[@class='nav-interact logout-click'][normalize-space()='Logout']")
	WebElement logout;
	
	public WebElement fetchProfileElement() {
		return profileName;
	}
	
	public void logoutToHome() {
		drop.click();
		logout.click();
	}

	
}
