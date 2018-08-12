package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ReadListPageObject;

public class iOSReadListPageObject extends ReadListPageObject {

    static {
        SAVED_ARTICLES = "xpath://XCUIElementTypeLink";
        SAVED_ARTICLE = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        EDIT_BUTTON = "id:Edit";
        UNSAVE_BUTTON = "id:Unsave";
    }

    public iOSReadListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
