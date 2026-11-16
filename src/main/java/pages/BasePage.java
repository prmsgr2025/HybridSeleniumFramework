
package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ElementNotInteractableException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    // ==================================================
    // 1. WAIT UTILITIES
    // ==================================================

    // Wait until element is visible
    protected WebElement waitForVisibility(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element is clickable
    protected WebElement waitForClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait until element is present in DOM
    protected WebElement waitForPresence(By locator) {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait until element becomes invisible or disappears
    protected boolean waitForInvisibility(By locator) {
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait until URL contains expected text
    protected boolean waitForUrlContains(String expectedUrl) {
        return wait.until(
                ExpectedConditions.urlContains(expectedUrl));
    }

    // Wait until title contains expected text
    protected boolean waitForTitleContains(String expectedTitle) {
        return wait.until(
                ExpectedConditions.titleContains(expectedTitle));
    }


    // ==================================================
    // 2. BASIC ELEMENT INTERACTIONS
    // ==================================================

    // Click element
    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    // Enter text
    protected void type(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(value);
    }

    // Clear text field
    protected void clear(By locator) {
        waitForVisibility(locator).clear();
    }

    // Get visible element text
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    protected String getTextAfterWait(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    // Get attribute value
    protected String getAttribute(By locator, String attribute) {
        return waitForVisibility(locator).getAttribute(attribute);
    }

    // Get element CSS property
    protected String getCssValue(By locator, String property) {
        return waitForVisibility(locator).getCssValue(property);
    }


    // ==================================================
    // 3. ELEMENT STATE VALIDATIONS
    // ==================================================

    // Check whether element is displayed
    protected boolean isDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);

        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    // Check whether element is enabled
    protected boolean isEnabled(By locator) {
        return waitForVisibility(locator).isEnabled();
    }

    // Check whether checkbox/radio is selected
    protected boolean isSelected(By locator) {
        return waitForPresence(locator).isSelected();
    }

    // Select checkbox only if not already selected
    protected void setCheckboxState(By locator, boolean shouldSelect) {
        WebElement checkbox = waitForClickable(locator);

        if (checkbox.isSelected() != shouldSelect) {
            checkbox.click();
        }
    }

    // Select radio button
    protected void selectRadioButton(By locator) {
        WebElement radio = waitForClickable(locator);

        if (!radio.isSelected()) {
            radio.click();
        }
    }


    // ==================================================
    // 4. STANDARD HTML DROPDOWN UTILITIES
    // ==================================================

    // Select dropdown by visible text
    protected void selectByVisibleText(By locator, String text) {
        Select dropdown = new Select(waitForVisibility(locator));
        dropdown.selectByVisibleText(text);
    }

    // Select dropdown by value
    protected void selectByValue(By locator, String value) {
        Select dropdown = new Select(waitForVisibility(locator));
        dropdown.selectByValue(value);
    }

    // Select dropdown by index
    protected void selectByIndex(By locator, int index) {
        Select dropdown = new Select(waitForVisibility(locator));
        dropdown.selectByIndex(index);
    }

    // Get currently selected dropdown text
    protected String getSelectedOption(By locator) {
        Select dropdown = new Select(waitForVisibility(locator));
        return dropdown.getFirstSelectedOption().getText();
    }


    // ==================================================
    // 5. SCROLL UTILITIES
    // ==================================================

    // Scroll element into view
    protected void scrollToElement(By locator) {
        WebElement element = waitForPresence(locator);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    // Scroll to page bottom
    protected void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scroll to page top
    protected void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, 0);");
    }


    // ==================================================
    // 6. JAVASCRIPT UTILITIES
    // ==================================================

    // Click using JavaScript
    protected void javaScriptClick(By locator) {
        WebElement element = waitForVisibility(locator);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", element);
    }

    // Get page title
    protected String getPageTitle() {
        return driver.getTitle();
    }

    // Get current URL
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    // ==================================================
    // 7. ALERT UTILITIES
    // ==================================================

    // Wait for alert
    protected Alert waitForAlert() {
        return wait.until(
                ExpectedConditions.alertIsPresent());
    }

    // Accept alert
    protected void acceptAlert() {
        waitForAlert().accept();
    }

    // Dismiss alert
    protected void dismissAlert() {
        waitForAlert().dismiss();
    }

    // Get alert text
    protected String getAlertText() {
        return waitForAlert().getText();
    }

    // Enter text into prompt alert
    protected void enterAlertText(String text) {
        Alert alert = waitForAlert();
        alert.sendKeys(text);
    }


    // ==================================================
    // 8. FRAME UTILITIES
    // ==================================================

    // Switch to frame using locator
    protected void switchToFrame(By locator) {
        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    // Switch back to main page
    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Switch to parent frame
    protected void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }


    // ==================================================
    // 9. WINDOW UTILITIES
    // ==================================================

    // Get current window handle
    protected String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    // Get all window handles
    protected Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    // Switch to window using handle
    protected void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    // Switch to newly opened window
    protected void switchToNewWindow(String previousHandle) {
        wait.until(driver ->
                driver.getWindowHandles().size() > 1);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(previousHandle)) {
                driver.switchTo().window(handle);
                return;
            }
        }

        throw new IllegalStateException(
                "New browser window was not found");
    }


    // ==================================================
    // 10. RETRY MECHANISMS
    // ==================================================

    // Click with retry for stale/intercepted elements
    protected void clickWithRetry(By locator, int maxAttempts) {

        RuntimeException lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            try {
                WebElement element = waitForClickable(locator);
                element.click();
                return;

            } catch (StaleElementReferenceException |
                     ElementClickInterceptedException e) {

                lastException = e;

                System.out.println(
                        "Click retry attempt: " + attempt
                );
            }
        }

        throw new RuntimeException(
                "Unable to click element after " + maxAttempts + " attempts: " + locator,
                lastException
        );
    }


    // Type with retry for stale elements
    protected void typeWithRetry(By locator, String value, int maxAttempts) {

        RuntimeException lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            try {
                WebElement element = waitForVisibility(locator);

                element.clear();
                element.sendKeys(value);

                return;

            } catch (StaleElementReferenceException e) {

                lastException = e;

                System.out.println(
                        "Typing retry attempt: " + attempt
                );
            }
        }

        throw new RuntimeException(
                "Unable to type after " + maxAttempts + " attempts: " + locator,
                lastException
        );
    }


    // ==================================================
    // 11. FALLBACK LOCATOR MECHANISM
    // ==================================================

    // Try primary locator, then secondary locator
    protected WebElement findElementWithFallback(
            By primaryLocator, By fallbackLocator) {

        try {
            return waitForVisibility(primaryLocator);

        } catch (TimeoutException | NoSuchElementException e) {

            System.out.println(
                    "Primary locator failed. Trying fallback locator: "
                            + fallbackLocator
            );

            return waitForVisibility(fallbackLocator);
        }
    }


    // Click using fallback locator
    protected void clickWithFallback(By primaryLocator, By fallbackLocator) {

        WebElement element = findElementWithFallback(
                primaryLocator, fallbackLocator);

        wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions
                        .elementToBeClickable(element)
        ).click();
    }


    // ==================================================
    // 12. DYNAMIC ELEMENT UTILITIES
    // ==================================================

    // Get all matching elements
    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    // Count matching elements
    protected int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }

    // Find element using dynamic XPath text
    protected By xpathByText(String text) {
        String safeText = text.replace("'", "\\'");
        return By.xpath("//*[normalize-space(text())='" + safeText + "']");
    }

    // Wait until text is present in an element
    protected boolean waitForText(By locator, String expectedText) {
        return wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions
                        .textToBePresentInElementLocated(
                                locator, expectedText)
        );
    }

    protected String getTextWithRetry(By locator, int attempts) {

        for (int i = 1; i <= attempts; i++) {

            try {
                WebElement element = waitForVisibility(locator);
                return element.getText();

            } catch (StaleElementReferenceException e) {

                //log.warn("Stale element encountered. Retry: {}", i);

                if (i == attempts) {
                    throw e;
                }
            }
        }

        throw new RuntimeException("Unable to retrieve element text");
    }

    // Close current window
    protected void closeCurrentWindow() {
        driver.close();
    }
}
