package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;


abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE,
            BACK_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void assertArticleTitlePresent(String title) {
        if (Platform.getInstance().isAndroid()) {
            this.assertElementPresent(ARTICLE_TITLE, ">>>>  Article title not found");
        } else if (Platform.getInstance().isIOS()) {
            String articleTitle = ARTICLE_TITLE.replace("{SUBSTRING}", title);
            this.assertElementPresent(articleTitle, ">>>>  Article title not found");
        }
    }

    public String getArticleTitle() {
        if (Platform.getInstance().isAndroid()) {
            return this.waitForElementPresent(
                    ARTICLE_TITLE,
                    ">>>> Error while getting the text value",
                    15
            ).getAttribute("text");
        } else {
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

    public void clickOnBackButton() {
        this.waitForElementAndClick(BACK_BUTTON, " >>>>> Can not find Back Button", 10);
    }


    public void assertArticlePresent(String articleTitle) {
        String articleXpath = (articleTitle);
        try {
            this.waitForElementPresent(
                    articleXpath,
                    " >>>>> Article " + articleTitle + " not found",
                    30
            );
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("An element '" + articleTitle + "' supposed to be present");
        }
    }


}
