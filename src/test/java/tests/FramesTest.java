
package tests;

import base.BaseTest;
import pages.FramesPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTest extends BaseTest {

    @Test
    public void verifyIframeInteraction() {

        FramesPage page = new FramesPage(getDriver());

        page.openPage();

        // Switch to iframe
        page.switchToEditorFrame();

        // Enter text
        page.enterEditorText("Selenium Frame Testing");

        // Validate editor content
        Assert.assertEquals(
                page.getEditorText(),
                "Selenium Frame Testing",
                "Iframe text validation failed"
        );

        // Return to main document
        page.switchBackToMainPage();

        // Validate parent page
        Assert.assertEquals(
                page.getMainPageHeading(),
                "An iFrame containing the TinyMCE WYSIWYG Editor"
        );
    }
}
