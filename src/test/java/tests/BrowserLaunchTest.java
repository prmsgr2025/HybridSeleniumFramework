
package tests;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserLaunchTest extends BaseTest {

    @Test
    public void verifyBrowserLaunch() throws InterruptedException {

        String title = getDriver().getTitle();

        System.out.println("Page Title: " + title);

        Assert.assertNotNull(
                title,
                "Page title should not be null"
        );

        Assert.assertFalse(
                title.isEmpty(),
                "Page title should not be empty"
        );
    }
}
