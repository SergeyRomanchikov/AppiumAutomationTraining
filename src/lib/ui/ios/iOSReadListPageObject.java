package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ReadListPageObject;

public class iOSReadListPageObject extends ReadListPageObject {

    static {
        SAVED_ARTICLES = "xpath://XCUIElementTypeLink";
        SAVED_ARTICLE = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
    }
    public iOSReadListPageObject(AppiumDriver driver){
        super(driver);
    }
}
