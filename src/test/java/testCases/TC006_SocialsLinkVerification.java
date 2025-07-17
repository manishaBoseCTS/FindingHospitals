package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FooterSocialLinks;
import testBase.BaseClass;
import utilities.ScreenShot;

public class TC006_SocialsLinkVerification extends BaseClass {

    @Test
    public void linkVerification() {
        logger.info("**** Starting Social Links Verification TestCase ****");

        try {
            FooterSocialLinks links = new FooterSocialLinks(driver);
            links.scrollToFooter();

            verifyAndCapture(links.facebook, "facebook");
            verifyAndCapture(links.twitter, "twitter");
            verifyAndCapture(links.linkedin, "linkedin");
            verifyAndCapture(links.youtube, "youtube");
            verifyAndCapture(links.github, "github");

            Assert.assertTrue(true);
        } catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage(), e);
            Assert.fail("Exception occurred: " + e.getMessage());
        }

        logger.info("**** Finished Social Links Verification TestCase ****");
    }

    public void verifyAndCapture(WebElement element, String name) throws Exception {
        String mainWindow = driver.getWindowHandle();
        element.click();
        Thread.sleep(3000);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                Thread.sleep(3000);
                ScreenShot.takeScreenShot(driver, name);
                driver.close();
                break;
            }
        }

        driver.switchTo().window(mainWindow);
    }
}

