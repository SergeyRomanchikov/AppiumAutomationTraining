package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ReadListPageObject;

public class AndroidReadListPageObject extends ReadListPageObject {

    static {
        CREATED_READ_LIST = "xpath://*[contains(@text, '{SUBSTRING}')]";
        SAVED_ARTICLES = "id:org.wikipedia:id/page_list_item_title";
        SAVED_ARTICLE = "xpath://*[contains(@text, '{SUBSTRING}')]";
        SNACK_BAR = "id:org.wikipedia:id/snackbar_text";
    }

    public AndroidReadListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
