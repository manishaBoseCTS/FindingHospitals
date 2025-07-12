package pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Instant_Video_Consultation extends BasePage{

	public Instant_Video_Consultation(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[contains(text(), '25+ Specialities')]")
	WebElement specialitiesHeading;
	
	@FindBy(xpath="//div[@class='top-speciality-card']")
	public List<WebElement> cards;
	
	@FindBy(tagName="h4")
	WebElement specialityName;
	
	@FindBy(tagName="p")
	WebElement specialityPrice;
	
	@FindBy(xpath="//button[@class='slider-next']")
	WebElement sliderBtn;
	
	public void scroll() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", specialitiesHeading);
        Thread.sleep(2000);
		}
	
	HashMap<String, String> map = new HashMap<>();
	
	public void putSpecialities(List<WebElement> cards) throws InterruptedException {
		for (WebElement card : cards) {
            String name = card.findElement(By.tagName("h4")).getText();
            String price = card.findElement(By.tagName("p")).getText();

            if (!name.isEmpty() && !price.isEmpty()) {
            		map.put(name, price);
            	}
        	}
		
		
		while (sliderBtn.isDisplayed()) {
            sliderBtn.click();
            Thread.sleep(1000);

            List<WebElement> newCards = driver.findElements(By.xpath("//*[@class='top-speciality-card']"));
            for (WebElement card : newCards) {
                String name = card.findElement(By.tagName("h4")).getText();
                String price = card.findElement(By.tagName("p")).getText();

                if (!name.isEmpty() && !price.isEmpty()) {
                		map.put(name, price);
                	}
            	}

        	}
		
		
		for (Map.Entry<String, String> entry : map.entrySet()) {	
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
		
	}
	
}
