package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReadArticles extends BasePage{

	public ReadArticles(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[@class='fit-feed-icon icon-ic_search_system']")
	WebElement search;
	
	@FindBy(xpath="//input[@id='searchBar']")
	WebElement input;
	
	@FindBy(xpath="//span[@class='fit-feed-icon icon-ic_search_system']")
	WebElement button;
	
	@FindBy(xpath = "//h2[@class='horizontal-card-title col-xs-12']")
    List<WebElement> articleTitles;

    @FindBy(xpath = "//div[@class='horizontal-card-author col-xs-12']")
    List<WebElement> articleAuthors;
	public void articleNameFunc(String article) throws Exception{
		search.click();
		Thread.sleep(5000);
		input.sendKeys(article);
		button.click();
		Thread.sleep(5000);
	}
	public void printSearchResults() {
        System.out.println("Search Results:");
        for (int i = 0; i < articleTitles.size(); i++) {
            String title = articleTitles.get(i).getText();
            String author = articleAuthors.get(i).getText();
            System.out.println("Article: " + title + "\n Author: " + author + "\n");
        }
    }
	public List<WebElement> getArticleTitles() {
        return articleTitles;
    }

    public List<WebElement> getArticleAuthors() {
        return articleAuthors;
    }
}
