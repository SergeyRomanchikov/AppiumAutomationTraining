package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {

        ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
