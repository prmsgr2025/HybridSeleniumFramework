
package factory;

import config.ConfigReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class DriverFactory {

    private static final Logger log =
            LogManager.getLogger(DriverFactory.class);

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initDriver() {

        String browser =
                ConfigReader.getProperty("browser");

        String executionMode =
                ConfigReader.getProperty("executionMode");

        WebDriver webDriver;

        try {

            if (executionMode.equalsIgnoreCase("local")) {

                webDriver = createLocalDriver(browser);

            } else if (executionMode.equalsIgnoreCase("remote")) {

                webDriver = createRemoteDriver(browser);

            } else {

                throw new IllegalArgumentException(
                        "Invalid execution mode: " + executionMode);
            }

            driver.set(webDriver);

            log.info(
                    "Browser initialized successfully. Browser: {}, Mode: {}, Thread: {}",
                    browser,
                    executionMode,
                    Thread.currentThread().getName()
            );

        } catch (Exception e) {

            log.error(
                    "Browser initialization failed for: {}",
                    browser,
                    e
            );

            throw new RuntimeException(
                    "Unable to initialize WebDriver", e);
        }
    }

    private static WebDriver createLocalDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                return new ChromeDriver(createChromeOptions());

            case "firefox":
                return new FirefoxDriver(createFirefoxOptions());

            case "edge":
                return new EdgeDriver(createEdgeOptions());

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser);
        }
    }

    private static WebDriver createRemoteDriver(String browser)
            throws Exception {

        URL gridUrl = new URL(
                ConfigReader.getProperty("gridUrl"));

        switch (browser.toLowerCase()) {

            case "chrome":
                return new RemoteWebDriver(
                        gridUrl,
                        createChromeOptions());

            case "firefox":
                return new RemoteWebDriver(
                        gridUrl,
                        createFirefoxOptions());

            case "edge":
                return new RemoteWebDriver(
                        gridUrl,
                        createEdgeOptions());

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser);
        }
    }

    private static ChromeOptions createChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");

        return options;
    }

    private static FirefoxOptions createFirefoxOptions() {

        FirefoxOptions options = new FirefoxOptions();

        return options;
    }

    private static EdgeOptions createEdgeOptions() {

        EdgeOptions options = new EdgeOptions();

        options.addArguments("--start-maximized");

        return options;
    }

    public static WebDriver getDriver() {

        WebDriver currentDriver = driver.get();

        if (currentDriver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized for this thread");
        }

        return currentDriver;
    }

    public static void quitDriver() {

        WebDriver currentDriver = driver.get();

        try {

            if (currentDriver != null) {

                currentDriver.quit();

                log.info(
                        "Browser closed successfully. Thread: {}",
                        Thread.currentThread().getName()
                );
            }

        } catch (Exception e) {

            log.error("Error while closing browser", e);

        } finally {

            driver.remove();
        }
    }
}
