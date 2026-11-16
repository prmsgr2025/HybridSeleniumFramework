package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WebFormPage extends BasePage {

    private final By textInput = By.name("my-text");
    private final By passwordInput = By.name("my-password");
    private final By textArea = By.name("my-textarea");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By confirmationMessage = By.tagName("h1");
    private final By checkbox1 = By.id("my-check-1");
    private final By checkbox2 = By.id("my-check-2");
    private final By radio1 = By.id("my-radio-1");
    private final By radio2 = By.id("my-radio-2");

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    public void enterText(String value) {
        type(textInput, value);
    }

    public void enterPassword(String value) {
        type(passwordInput, value);
    }

    public void enterTextArea(String value) {
        type(textArea, value);
    }

    public void clickSubmit() {
        click(submitButton);
    }

    public String getConfirmationMessage() {
        return getTextAfterWait(confirmationMessage);
    }

    public String getPageTitle() {
        return super.getPageTitle();
    }

    // Checkbox operations
    public void selectCheckbox1() {
        setCheckboxState(checkbox1, true);
    }

    public void deselectCheckbox1() {
        setCheckboxState(checkbox1, false);
    }

    public boolean isCheckbox1Selected() {
        return isSelected(checkbox1);
    }

    public void selectCheckbox2() {
        setCheckboxState(checkbox2, true);
    }

    public boolean isCheckbox2Selected() {
        return isSelected(checkbox2);
    }

// Radio button operations
    public void selectRadio1() {
        selectRadioButton(radio1);
    }

    public void selectRadio2() {
        selectRadioButton(radio2);
    }

    public boolean isRadio1Selected() {
        return isSelected(radio1);
    }

    public boolean isRadio2Selected() {
        return isSelected(radio2);
    }

    public void submitForm() {
        click(submitButton);
    }
}