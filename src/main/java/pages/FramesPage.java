
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FramesPage extends BasePage {

    private final By iframe = By.id("mce_0_ifr");

    // TinyMCE editable body inside the iframe
    private final By editorBody = By.id("tinymce");

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(
                "https://the-internet.herokuapp.com/iframe");
    }

    public void switchToEditorFrame() {
        switchToFrame(iframe);
    }

    public void enterEditorText(String value) {

        WebElement editor = waitForVisibility(editorBody);

        editor.click();

        // Select existing content
        editor.sendKeys(Keys.CONTROL, "a");

        // Enter new content
        editor.sendKeys(value);
    }

    public String getEditorText() {

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        editorBody,
                        "Selenium Frame Testing"
                )
        ) ? driver.findElement(editorBody).getText() : "";
    }

    public void switchBackToMainPage() {
        switchToDefaultContent();
    }

    public String getMainPageHeading() {
        return getText(By.tagName("h3"));
    }
}
