package utilities;

import java.io.File;
import java.io.IOException;

public class AllureReportOpener {

    public static void openAllureReport() {
        try {
            // Get current working directory
            String projectPath = new File("").getAbsolutePath();

            // Construct batch file path
            String batchFile = projectPath + File.separator + "allureReport.bat";

            // Step 1: Generate Allure report
            ProcessBuilder generate = new ProcessBuilder(
                    batchFile, "generate", "target/allure-results", "-o", "allure-report", "--clean");
            generate.inheritIO();
            Process genProcess = generate.start();
            genProcess.waitFor();

            // Step 2: Open Allure report
            ProcessBuilder open = new ProcessBuilder(batchFile, "open", "allure-report");
            open.inheritIO();
            Process openProcess = open.start();
            openProcess.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
