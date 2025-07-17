package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterSocialLinks extends BasePage{

	public FooterSocialLinks(WebDriver driver) {
		super(driver);
	}
	
	 @FindBy(xpath = "//*[@id='root']/div/div/footer/div/div[1]/div[6]/div[2]/a[1]")
	 public WebElement facebook;

    @FindBy(xpath = "//*[@id='root']/div/div/footer/div/div[1]/div[6]/div[2]/a[2]")
    public WebElement twitter;

    @FindBy(xpath = "//*[@id='root']/div/div/footer/div/div[1]/div[6]/div[2]/a[3]")
    public WebElement linkedin;

    @FindBy(xpath = "//*[@id='root']/div/div/footer/div/div[1]/div[6]/div[2]/a[4]")
    public WebElement youtube;

    @FindBy(xpath = "//*[@id='root']/div/div/footer/div/div[1]/div[6]/div[2]/a[5]")
    public WebElement github;

    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
