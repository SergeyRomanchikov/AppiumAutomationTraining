package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;


public class ArticlePageObject extends MainPageObject {

    private static final String
            ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void assertArticleTitlePresent() {
        this.assertElementPresent(ARTICLE_TITLE, ">>>>  Article title not found");
    }

    public String getArticleTitle() {
        return this.waitForElementPresent(
                ARTICLE_TITLE,
                ">>>> Error while getting the text value",
                15
        ).getAttribute("text");
    }

    public void assertTitle(String articleToCheckTitle, String currentArticleTitle) {
        Assert.assertEquals(
                ">>>>  Title '" + currentArticleTitle + "' not equals '" + articleToCheckTitle + "'",
                articleToCheckTitle,
                currentArticleTitle);

    }


}
