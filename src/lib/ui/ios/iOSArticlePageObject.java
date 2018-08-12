package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "id:{SUBSTRING}";
        BACK_BUTTON = "id:Back";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
