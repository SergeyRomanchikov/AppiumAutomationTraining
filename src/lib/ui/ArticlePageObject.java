package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;


abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE,
            BACK_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void assertArticleTitlePresent() {
        this.assertElementPresent(ARTICLE_TITLE, ">>>>  Article title not found");
    }

    public String getArticleTitle() {
        if (Platform.getInstance().isAndroid()){
            return this.waitForElementPresent(
                    ARTICLE_TITLE,
                    ">>>> Error while getting the text value",
                    15
            ).getAttribute("text");
        }else {
            return this.waitForElementPresent(
                    ARTICLE_TITLE,
                    ">>>> Error while getting the text value",
                    15
            ).getAttribute("name");
        }

    }

    public void assertTitle(String articleToCheckTitle, String currentArticleTitle) {
        Assert.assertEquals(
                ">>>>  Title '" + currentArticleTitle + "' not equals '" + articleToCheckTitle + "'",
                articleToCheckTitle,
                currentArticleTitle);

    }

    public void clickOnBackButton(){
        this.waitForElementAndClick(BACK_BUTTON, " >>>>> Can not find Back Button", 10);
    }


}
