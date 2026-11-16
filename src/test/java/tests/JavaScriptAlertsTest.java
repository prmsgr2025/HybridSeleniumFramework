
package tests;

import base.BaseTest;
import pages.JavaScriptAlertsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaScriptAlertsTest extends BaseTest {

    @Test
    public void verifySimpleAlert() {

        JavaScriptAlertsPage page =
                new JavaScriptAlertsPage(getDriver());

        page.openPage();
        page.clickAlert();

        Assert.assertEquals(
                page.getAlertMessage(),
                "I am a JS Alert"
        );

        page.acceptAlertMessage();

        Assert.assertEquals(
                page.getResult(),
                "You successfully clicked an alert"
        );
    }

    @Test
    public void verifyConfirmationAccept() {

        JavaScriptAlertsPage page =
                new JavaScriptAlertsPage(getDriver());

        page.openPage();
        page.clickConfirm();

        page.acceptAlertMessage();

        Assert.assertEquals(
                page.getResult(),
                "You clicked: Ok"
        );
    }

    @Test
    public void verifyConfirmationDismiss() {

        JavaScriptAlertsPage page =
                new JavaScriptAlertsPage(getDriver());

        page.openPage();
        page.clickConfirm();

        page.dismissAlertMessage();

        Assert.assertEquals(
                page.getResult(),
                "You clicked: Cancel"
        );
    }

    @Test
    public void verifyPromptAlert() {

        JavaScriptAlertsPage page =
                new JavaScriptAlertsPage(getDriver());

        page.openPage();
        page.clickPrompt();

        page.enterPromptText("Hello Selenium");
        page.acceptAlertMessage();

        Assert.assertEquals(
                page.getResult(),
                "You entered: Hello Selenium"
        );
    }
}
