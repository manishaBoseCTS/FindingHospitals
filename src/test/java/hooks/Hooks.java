package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;

public class Hooks {
    public static WebDriver driver;
    public static Logger logger;
    public static Properties p;

    @Before
    public void setUp() throws IOException {
        p = new Properties();
        FileInputStream file = new FileInputStream("src/test/resources/config.properties");
        p.load(file);

        String baseURL = p.getProperty("appURL");
        String os = p.getProperty("os");
        String browser = p.getProperty("browser");

        logger = LogManager.getLogger(Hooks.class);

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(os.equalsIgnoreCase("windows") ? Platform.WINDOWS : Platform.MAC);
            capabilities.setBrowserName(browser.toLowerCase());
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } else {
            switch (browser.toLowerCase()) {
                case "chrome": driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                default: throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshotBytes));
        }

        if (driver != null) {
            driver.quit();
        }
    }
}

