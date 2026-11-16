package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            "test-output/ExtentReport.html");

            sparkReporter.config().setDocumentTitle(
                    "Hybrid Automation Framework Report");

            sparkReporter.config().setReportName(
                    "Automation Execution Report");

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Framework",
                    "Hybrid Selenium Framework");

            extent.setSystemInfo("Automation Tool", "Selenium");

            extent.setSystemInfo("Test Framework", "TestNG");
        }

        return extent;
    }

    public static void createTest(String testName) {
        ExtentTest extentTest =
                getInstance().createTest(testName);

        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void removeTest() {
        test.remove();
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
