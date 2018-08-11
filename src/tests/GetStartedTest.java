package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {

        if (Platform.getInstance().isAndroid()) {
            return;
        }

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForElementLearnMoreLink();
        WelcomePage.clickOnNextButton();
        WelcomePage.waitForElementNewWaysToExplore();
        WelcomePage.clickOnNextButton();
        WelcomePage.waitForElementAddOrEditPreferredLanguages();
        WelcomePage.clickOnNextButton();
        WelcomePage.waitForElementLearnMoreAboutDataCollected();
        WelcomePage.clickOnGetStartedButton();

    }
}
