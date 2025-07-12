package utilities;
 
import java.time.Duration;
 
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class WaitUtils
{
    public static void waitForDuration(WebDriver driver, int seconds)
    {
        try
        {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(d -> false); // Always false, just to wait
        } catch (TimeoutException e) {
        	
        }
    }
}
 