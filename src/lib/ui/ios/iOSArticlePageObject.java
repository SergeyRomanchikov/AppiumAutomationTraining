package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";
        BACK_BUTTON = "id:Back";
    }

    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
