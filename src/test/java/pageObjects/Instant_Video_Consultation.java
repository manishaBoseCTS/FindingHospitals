package pageObjects;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;
import utilities.WaitUtils;

public class Instant_Video_Consultation extends BasePage {

	public Instant_Video_Consultation(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[contains(text(), '25+ Specialities')]")
	WebElement specialitiesHeading;

	@FindBy(xpath = "//div[@class='top-speciality-card']")
	public List<WebElement> cards;

	@FindBy(tagName = "h4")
	WebElement specialityName;

	@FindBy(tagName = "p")
	WebElement specialityPrice;

	@FindBy(xpath = "//button[@class='slider-next']")
	WebElement sliderBtn;

	public void scroll() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", specialitiesHeading);
		WaitUtils.waitForDuration(driver, 2);
	}

	HashMap<String, String> map = new HashMap<>();

	public void putSpecialities(List<WebElement> cards) throws InterruptedException, IOException {
		for (WebElement card : cards) {
			String name = card.findElement(By.tagName("h4")).getText();
			String price = card.findElement(By.tagName("p")).getText();

			if (!name.isEmpty() && !price.isEmpty()) {
				map.put(name, price);
			}
		}

		while (sliderBtn.isDisplayed()) {
			sliderBtn.click();
			// WaitUtils.waitForDuration(driver, 2);

			List<WebElement> newCards = driver.findElements(By.xpath("//*[@class='top-speciality-card']"));
			for (WebElement card : newCards) {
				String name = card.findElement(By.tagName("h4")).getText();
				String price = card.findElement(By.tagName("p")).getText();

				if (!name.isEmpty() && !price.isEmpty()) {
					map.put(name, price);
				}
			}

		}
		
		String path = ".\\testData\\InstantVideoConsultation.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			//System.out.println(entry.getKey() + " :: " + entry.getValue());
			xlutil.setCellData("TC008_Output", i, 0, entry.getKey());
			xlutil.setCellData("TC008_Output", i, 1, entry.getValue());
			i++;
		}

	}

}
