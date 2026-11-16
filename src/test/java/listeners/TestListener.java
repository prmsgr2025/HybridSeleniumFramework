
package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.getInstance();
        System.out.println("Test Suite Started: "
                + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        String executionDetails =
                java.util.Arrays.deepToString(result.getParameters());

        ExtentManager.createTest(
                testName + " - " + executionDetails);

        ExtentManager.getTest().log(
                Status.INFO, "Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentManager.getTest().pass(
                "Test Passed Successfully");

        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest extentTest = ExtentManager.getTest();

        extentTest.fail(result.getThrowable());

        try {
            WebDriver driver = DriverFactory.getDriver();

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            driver,
                            result.getMethod().getMethodName());

            extentTest.fail(
                    "Screenshot on failure",
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(
                                    screenshotPath)
                            .build()
            );

        } catch (Exception e) {

            extentTest.warning(
                    "Screenshot could not be captured: "
                            + e.getMessage());
        }

        ExtentManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentManager.getTest().skip(
                "Test Skipped: " + result.getName());

        if (result.getThrowable() != null) {
            ExtentManager.getTest().skip(
                    result.getThrowable());
        }

        ExtentManager.removeTest();
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentManager.flushReport();

        System.out.println("Test Suite Finished: "
                + context.getName());
    }
}
