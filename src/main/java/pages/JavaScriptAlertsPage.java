
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage extends BasePage {

    private final By alertButton =
            By.xpath("//button[text()='Click for JS Alert']");

    private final By confirmButton =
            By.xpath("//button[text()='Click for JS Confirm']");

    private final By promptButton =
            By.xpath("//button[text()='Click for JS Prompt']");

    private final By result =
            By.id("result");

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(
                "https://the-internet.herokuapp.com/javascript_alerts");
    }

    public void clickAlert() {
        click(alertButton);
    }

    public void clickConfirm() {
        click(confirmButton);
    }

    public void clickPrompt() {
        click(promptButton);
    }

    public String getAlertMessage() {
        return getAlertText();
    }

    public void acceptAlertMessage() {
        acceptAlert();
    }

    public void dismissAlertMessage() {
        dismissAlert();
    }

    public void enterPromptText(String value) {
        enterAlertText(value);
    }

    public String getResult() {
        return getText(result);
    }
}
