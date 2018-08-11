package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        MY_READ_LIST_BUTTON = "xpath://*[contains(@content-desc, 'My lists')]";
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_LINE_ELEMENT = "id:org.wikipedia:id/search_src_text";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_ARTICLE_ID = "id:org.wikipedia:id/page_list_item_title";
        ADD_TO_READLIST_BUTTON = "xpath:/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
        INPUT_READ_LIST_NAME = "id:org.wikipedia:id/text_input";
        DIALOG_OK_BUTTON = "id:android:id/button1";
        WIKIPEDIA_LOGO_ON_MAIN_PAGE = "id:org.wikipedia:id/single_fragment_toolbar_wordmark";
        SEARCH_RESULT = "xpath://*[@text='{ARTICLE_DESCRIPTION}']/../*[@text='{ARTICLE_TITLE}']/..";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {

        super(driver);
    }
}
