
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowsPage extends BasePage {

    private final By openWindowLink =
            By.linkText("Click Here");

    private final By childHeading =
            By.tagName("h3");

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(
                "https://the-internet.herokuapp.com/windows");
    }

    public void clickOpenWindow() {
        click(openWindowLink);
    }

    public String getChildWindowHeading() {
        return getText(childHeading);
    }

    public void switchToChildWindow(String parentHandle) {
        switchToNewWindow(parentHandle);
    }

    public void closeChildWindow() {
        closeCurrentWindow();
    }

    public void switchToParentWindow(String parentHandle) {
        switchToWindow(parentHandle);
    }
}
