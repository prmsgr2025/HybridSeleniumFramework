
package tests;

import base.BaseTest;
import pages.WindowsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowsTest extends BaseTest {

    @Test
    public void verifyMultipleWindows() {

        WindowsPage page = new WindowsPage(getDriver());

        // Open application
        page.openPage();

        // Capture parent window handle
        String parentWindow = getDriver().getWindowHandle();

        // Open child window
        page.clickOpenWindow();

        // Switch to child window
        page.switchToChildWindow(parentWindow);

        // Validate child window
        Assert.assertEquals(
                page.getChildWindowHeading(),
                "New Window"
        );

        // Close child window
        page.closeChildWindow();

        // Switch back to parent
        page.switchToParentWindow(parentWindow);

        // Validate parent window
        Assert.assertEquals(
                getDriver().getTitle(),
                "The Internet"
        );
    }
}
