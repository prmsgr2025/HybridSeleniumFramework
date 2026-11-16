
package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    public static String captureScreenshot(
            WebDriver driver, String testName) {

        try {
            Path directory = Paths.get(
                    "test-output", "screenshots");

            Files.createDirectories(directory);

            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

            String fileName = testName.replaceAll(
                    "[^a-zA-Z0-9-_]", "_")
                    + "_" + timestamp + ".png";

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path destination = directory.resolve(fileName);

            Files.copy(source.toPath(), destination);

            return destination.toAbsolutePath().toString();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to capture screenshot", e);
        }
    }
}
