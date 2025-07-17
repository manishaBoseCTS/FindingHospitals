package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass
	@Parameters({"os", "browser"})
	public void setup(String os, String browser) throws IOException {
	    p = new Properties();
	    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
	    p.load(file);
	    String baseURL = p.getProperty("appURL");

	    logger = LogManager.getLogger(this.getClass());

	    if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
	        DesiredCapabilities capabilities = new DesiredCapabilities();

	        if (os.equalsIgnoreCase("windows")) {
	            capabilities.setPlatform(Platform.WINDOWS);
	        } else if (os.equalsIgnoreCase("mac")) {
	            capabilities.setPlatform(Platform.MAC);
	        } else {
	            System.out.println("No matching OS");
	            return;
	        }

	        switch (browser.toLowerCase()) {
	            case "chrome": capabilities.setBrowserName("chrome"); break;
	            case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
	            case "firefox": capabilities.setBrowserName("firefox"); break;
	            default: System.out.println("No matching browser"); return;
	        }

	        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
	    } else {
	        switch (browser.toLowerCase()) {
	            case "chrome": driver = new ChromeDriver(); break;
	            case "edge": driver = new EdgeDriver(); break;
	            case "firefox": driver = new FirefoxDriver(); break;
	            default: System.out.println("Invalid browser name"); return;
	        }
	    }

	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
	    driver.get(baseURL);
	    driver.manage().window().maximize();
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
