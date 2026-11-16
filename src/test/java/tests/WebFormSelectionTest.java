package tests;

import base.BaseTest;
import pages.WebFormPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WebFormSelectionTest extends BaseTest {

    @Test
    public void verifyCheckboxSelection() {

        WebFormPage formPage =
                new WebFormPage(getDriver());

        formPage.selectCheckbox1();

        Assert.assertTrue(
                formPage.isCheckbox1Selected(),
                "Checkbox 1 should be selected"
        );

        formPage.deselectCheckbox1();

        Assert.assertFalse(
                formPage.isCheckbox1Selected(),
                "Checkbox 1 should be deselected"
        );

        formPage.selectCheckbox2();

        Assert.assertTrue(
                formPage.isCheckbox2Selected(),
                "Checkbox 2 should be selected"
        );
    }

    @Test
    public void verifyRadioButtonSelection() {

        WebFormPage formPage =
                new WebFormPage(getDriver());

        formPage.selectRadio1();

        Assert.assertTrue(
                formPage.isRadio1Selected(),
                "Radio 1 should be selected"
        );

        formPage.selectRadio2();

        Assert.assertTrue(
                formPage.isRadio2Selected(),
                "Radio 2 should be selected"
        );

        Assert.assertFalse(
                formPage.isRadio1Selected(),
                "Radio 1 should be deselected"
        );
    }
}