package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
public class ForCorporates_Captcha extends BasePage {
	
	public ForCorporates_Captcha(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//*[@id='name']")
	WebElement Name;
	@FindBy(xpath="//input[@id='organizationName']")
	WebElement orgName;
	@FindBy(xpath="//input[@id='contactNumber']")
	WebElement contact;
	@FindBy(xpath="//*[@id='officialEmailId']")
	WebElement email;
	@FindBy(id = "organizationSize")
	WebElement orgSize;
	@FindBy(id = "interestedIn")
	WebElement interestedIn;
	@FindBy(xpath="//header[@id='header']//button[@type='submit'][normalize-space()='Schedule a demo']")
	WebElement submit;
	public void nameFunc(String name) throws Exception{
		Name.click();
		Thread.sleep(1000);
		Name.sendKeys(name);
		Thread.sleep(2000);
	}
	public void orgFunc(String org) throws Exception{
		orgName.click();
		Thread.sleep(1000);
		orgName.sendKeys(org);
		Thread.sleep(2000);
	}
	public void contactFunc(String num) throws Exception{
		contact.click();
		Thread.sleep(1000);
		contact.sendKeys(num);
		Thread.sleep(2000);
	}
	public void emailFunc(String emailId) throws Exception{
		email.click();
		Thread.sleep(1000);
		email.sendKeys(emailId);
		Thread.sleep(2000);
	}
	
	public void selectOrgSize(String value) {
	    Select select = new Select(orgSize);
	    select.selectByValue(value); 
	}
	
	public void selectInterest(String value) {
	    Select select = new Select(interestedIn);
	    select.selectByValue(value); 
	}
	public void scheduleADemo() throws Exception {
		Thread.sleep(2000);
		submit.click();
		
	}

}
