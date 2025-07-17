/**
 * File Name: ScreenShot.java
 * Description:
 * This utility class provides a method to capture and save screenshots during test execution.
 * Screenshots are saved with a timestamp to avoid overwriting and to help with debugging and reporting.

 * Purpose:
 * Used to visually document the state of the application at various steps in the test flow.

 * Methods:
 * - takeScreenShot: Captures a screenshot of the current browser window
 *   and saves it in the "Screenshots" directory with a timestamped filename.
 */

package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {

	public static String filePath = System.getProperty("user.dir") + "\\Screenshots\\";

	public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		File ssFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(ssFile, new File(filePath + fileName + "_" + dateFormat.format(date) + ".png"));
			System.out.println("\t\tScreenshot Taken for " + fileName);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
