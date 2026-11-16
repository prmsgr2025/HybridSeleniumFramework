
package base;

import config.ConfigReader;
import factory.DriverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    private static final Logger log =
            LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {

        log.info("Starting test setup");

        DriverFactory.initDriver();

        WebDriver driver = DriverFactory.getDriver();

        driver.manage().window().maximize();

        String baseUrl =
                ConfigReader.getProperty("baseUrl");

        driver.get(baseUrl);

        log.info("Application launched: {}", baseUrl);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        log.info("Starting browser teardown");

        DriverFactory.quitDriver();
    }
}
