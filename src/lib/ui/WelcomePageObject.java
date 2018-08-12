package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP = "id:Skip";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForElementLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, ">>>>> Element 'Learn more about Wikipedia' not found", 10);
    }

    public void waitForElementNewWaysToExplore() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, ">>>>> Element 'New ways to explore' not found", 10);
    }

    public void waitForElementAddOrEditPreferredLanguages() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK, ">>>>> Element 'Add or edit preferred languages' not found", 10);
    }

    public void waitForElementLearnMoreAboutDataCollected() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, ">>>>> Element 'Learn more about data collected' not found", 10);
    }

    public void clickOnNextButton() {
        this.waitForElementAndClick(NEXT_LINK, ">>>>> Element 'Next Button' not found", 10);
    }

    public void clickOnGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, ">>>>> Element 'Get started' not found", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, ">>>>> Element 'Skip' not found", 5);
    }
}
