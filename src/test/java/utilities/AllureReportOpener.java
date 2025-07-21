package utilities;

import java.io.IOException;

public class AllureReportOpener {
	
	public static void openAllureReport() {
		try {
			// Step 1: Generate Allure report
			ProcessBuilder generate = new ProcessBuilder(

					"C:\\Users\\2403730\\eclipse-workspace\\FindingHospitals-main\\allureReport.bat",
					"generate", "target/allure-results", "-o", "/allure-report", "--clean");
			generate.inheritIO(); // Optional: shows output in console
			Process genProcess = generate.start();
			genProcess.waitFor();

			// Step 2: Open Allure report in browser
			ProcessBuilder open = new ProcessBuilder(
					"C:\\Users\\2403730\\eclipse-workspace\\FindingHospitals-main\\allureReport.bat", "open",
					"/allure-report");
			open.inheritIO();
			Process openProcess = open.start();
			openProcess.waitFor();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
