package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class Login extends BasePage{

    WebDriver driver;

    public Login(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    public WebElement userName;

    @FindBy(id = "password")
   public WebElement password;

    @FindBy(xpath = "//div[@class='remember']//label[@for='remember']")
    WebElement rememberBtn;

    @FindBy(id = "login")
    public WebElement loginBtn;

    public void loginWithCredentials(String email, String pwd) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(userName));

        userName.clear();
        userName.sendKeys(email);

        password.clear();
        password.sendKeys(pwd);

        rememberBtn.click();
        loginBtn.click();
    }

}



