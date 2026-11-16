
package tests;

import base.BaseTest;
import dataproviders.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WebFormPage;

import java.util.Map;

public class DataDrivenWebFormTest extends BaseTest {

    @Test(dataProvider = "webFormTestData",
            dataProviderClass = TestDataProvider.class)
    public void verifyWebFormSubmission(Map<String, Object> data) {

        WebFormPage webFormPage = new WebFormPage(getDriver());

        System.out.println(
                "Test Case: " + data.get("testCase")
                        + " | Thread: " + Thread.currentThread().getName()
        );

        String inputText = data.get("inputText").toString();
        String expectedMessage =
                data.get("expectedMessage").toString();

        webFormPage.enterText(inputText);

        webFormPage.submitForm();

        // Debug information
        System.out.println("Current URL: "
                + getDriver().getCurrentUrl());

        System.out.println("Page Title: "
                + getDriver().getTitle());

        System.out.println("Confirmation H1: "
                + webFormPage.getConfirmationMessage());

        String actualMessage = webFormPage.getConfirmationMessage();

        System.out.println("Confirmation Message: " + actualMessage);

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Form submission message mismatch for: " + data.get("testCase")
        );
    }

    @Test
    public void verifyFailureScreenshot() {

        Assert.fail("Intentional failure to validate screenshot");
    }

    @Test
    public void verifyRetryMechanism() {

        throw new org.openqa.selenium.TimeoutException(
                "Intentional timeout for retry validation");
    }
}
