package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        MY_READ_LIST_BUTTON = "id:Saved";
        SYNC_DIALOG_CLOSE_BUTTON = "id:places auth close";
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_LINE_ELEMENT = "xpath://XCUIElementTypeSearchField[contains(@value, 'Search Wikipedia')]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_ARTICLE_ID = "xpath://XCUIElementTypeCell/XCUIElementTypeLink";
        ADD_TO_READLIST_BUTTON = "id:Save for later";
        WIKIPEDIA_LOGO_ON_MAIN_PAGE = "id:org.wikipedia:id/single_fragment_toolbar_wordmark";
        SEARCH_RESULT = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_TITLE}\n{ARTICLE_DESCRIPTION}')]";
    }

    public iOSSearchPageObject(AppiumDriver driver) {

        super(driver);
    }
}
