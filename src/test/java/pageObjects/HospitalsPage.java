package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HospitalsPage extends BasePage{
	public HospitalsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='c-prime-header']/h1")
	WebElement pageNo;
	
	@FindBy(xpath="//li")
	List<WebElement> hospitals;
	
	@FindBy(xpath="//*[@class='pd-right-2px-text-green']")
	List<WebElement> openTime;
	
	@FindBy(xpath="//*[@class='text-1']/*[@class='u-bold']")
	List<WebElement> rating;
	
	public void getResult() {
		//System.out.println(pageNo.getText());
	}
	public List<String> getHospitalNames(String timing, double rating) {
		List<WebElement> openReqTime = new ArrayList<>();
		List<WebElement> finalHospitals = new ArrayList<>();
		for(WebElement h: hospitals) {
			WebElement timeElement = h.findElement(By.className("pd-right-2px-text-green"));
			String openDuration = timeElement.getText();
			if(openDuration.equals(timing))
				openReqTime.add(h);
		}
		for(WebElement o: openReqTime) {
			WebElement ratingElement = o.findElement(By.cssSelector(".text-1 .u-bold"));
			double rH = Double.parseDouble(ratingElement.getText());
			if(rH>=rating)
				finalHospitals.add(o);
		}
		
		List<String> names = new ArrayList<>();
		//printing Hospitals
		for(WebElement h: finalHospitals) {
			String name = h.findElement(By.tagName("h2")).getText();
			names.add(name);
			//System.out.println(name);
		}
		return names;
	}
}
