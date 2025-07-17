package testCases;

import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import pageObjects.HomePage;
import pageObjects.ForCorporates_Captcha;
import testBase.BaseClass;
import utilities.ScreenShot;
import utilities.xmlHelper;

import java.time.Duration;

public class TC007_CaptchaScreenshot extends BaseClass {
    @Test
    public void corporateClick() throws Exception {
        logger.info("**********Starting: from For Corporates");

        try {
            HomePage homepage = new HomePage(driver);
            homepage.forCorporateClickFunc();
            logger.info("********Clicked 'For Corporate Link'********");

            homepage.forHealthFunc();
            logger.info("********Clicked 'For Health and Wellness Link'********");

            Thread.sleep(2000);
            logger.info("Current URL after clicking: " + driver.getCurrentUrl());

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
            logger.info("Form is loaded — ready to interact");

            String currentHandle = driver.getWindowHandle();
            logger.info("Current active window handle: " + currentHandle);

            ForCorporates_Captcha corporateHealth = new ForCorporates_Captcha(driver);
            corporateHealth.nameFunc(xmlHelper.getData("name"));
            logger.info("********Name********");

            corporateHealth.orgFunc(xmlHelper.getData("organization"));
            logger.info("*******Organization********");

            corporateHealth.contactFunc(xmlHelper.getData("contact"));
            logger.info("********Contact********");
            
            corporateHealth.emailFunc(xmlHelper.getData("email"));
            logger.info("********Email********");

            corporateHealth.selectOrgSize(xmlHelper.getData("organizationSize"));
            logger.info("********Organization Size********");

            corporateHealth.selectInterest(xmlHelper.getData("interest"));
            logger.info("********Interest********");
            
            corporateHealth.scheduleADemo();
            logger.info("********Clicked submit********");
            
            ScreenShot.takeScreenShot(driver, "CaptchaAfterSubmit");
            logger.info("***Screenshot captured: CaptchaAfterSubmit*****");
      

        } catch (Exception e) {
            logger.error("***********Test Failed — Reason: " + e.getMessage(), e);
            logger.debug("Debug logs....");
            e.printStackTrace();
        }
    }
}

