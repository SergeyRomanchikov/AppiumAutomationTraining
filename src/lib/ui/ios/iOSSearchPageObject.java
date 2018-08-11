package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        MY_READ_LIST_BUTTON = "xpath://*[contains(@content-desc, 'My lists')]";
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_LINE_ELEMENT = "xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_ARTICLE_ID = "xpath://XCUIElementTypeCell";
        ADD_TO_READLIST_BUTTON = "id:Save for later";
        WIKIPEDIA_LOGO_ON_MAIN_PAGE = "id:org.wikipedia:id/single_fragment_toolbar_wordmark";
        // SEARCH_RESULT = "xpath://*[@text='{ARTICLE_DESCRIPTION}']/../*[@text='{ARTICLE_TITLE}']/..";
        SEARCH_RESULT = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_TITLE}\n{ARTICLE_DESCRIPTION}')]";

    }

    public iOSSearchPageObject(AppiumDriver driver) {

        super(driver);
    }
}
