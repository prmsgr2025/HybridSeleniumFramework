package tests;

import base.BaseTest;
import pages.WebFormPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WebFormTest extends BaseTest {

    @Test
    public void verifyWebFormSubmission() {

        WebFormPage formPage =
                new WebFormPage(getDriver());

        formPage.enterText("Selenium Automation");
        formPage.enterPassword("Test123");
        formPage.enterTextArea("Testing Page Object Model");

        formPage.clickSubmit();

        String actualMessage = formPage.getConfirmationMessage();

        Assert.assertEquals(
                actualMessage,
                "Form submitted",
                "Confirmation message mismatch"
        );
    }
}