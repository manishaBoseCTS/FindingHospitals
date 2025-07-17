package stepDefinitions;

import org.openqa.selenium.WebElement;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import hooks.Hooks;
import pageObjects.FooterSocialLinks;
import utilities.ScreenShot;

public class TC006_SocialsLinkVerificationSteps {
    FooterSocialLinks links;

    @Given("User is on the website and scrolls to footer")
    public void user_scrolls_to_footer() {
        Hooks.logger.info("Scrolling to footer section");
        links = new FooterSocialLinks(Hooks.driver);
        links.scrollToFooter();
    }

    @Then("User verifies and captures the screenshot of {string} social link")
    public void user_verifies_and_captures_link(String platform) throws Exception {
        WebElement element = null;
        switch (platform.toLowerCase()) {
            case "facebook": element = links.facebook; break;
           // case "twitter": element = links.twitter; break;
            case "linkedin": element = links.linkedin; break;
            case "youtube": element = links.youtube; break;
            case "github": element = links.github; break;
            default:
                Hooks.logger.error("Invalid social link: " + platform);
                Assert.fail("Unknown social link platform: " + platform);
        }

        String mainWindow = Hooks.driver.getWindowHandle();
        element.click();
        Thread.sleep(3000);

        for (String handle : Hooks.driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                Hooks.driver.switchTo().window(handle);
                Thread.sleep(3000);
                ScreenShot.takeScreenShot(Hooks.driver, platform);
                Hooks.driver.close();
                break;
            }
        }

        Hooks.driver.switchTo().window(mainWindow);
    }
}
