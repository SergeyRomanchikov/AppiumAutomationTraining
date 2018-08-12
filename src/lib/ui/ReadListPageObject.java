package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class ReadListPageObject extends MainPageObject {

    protected static String
            CREATED_READ_LIST,
            SAVED_ARTICLES,
            SAVED_ARTICLE,
            SNACK_BAR,
            UNSAVE_BUTTON,
            EDIT_BUTTON;


    public ReadListPageObject(AppiumDriver driver) {
        super(driver);
    }

    // TEMPLATES METHODS //

    private static String searchCreatedReadListXPath(String readListName) {
        return CREATED_READ_LIST.replace("{SUBSTRING}", readListName);
    }

    private static String savedArticle(String articleTitle) {
        return SAVED_ARTICLE.replace("{SUBSTRING}", articleTitle);
    }

    // TEMPLATES METHODS //

    public void clickOnCreatedRedList(String readListName) {
        String currentReadListXPath = searchCreatedReadListXPath(readListName);
        this.waitForElementAndClick(currentReadListXPath, "Cannot find " + readListName + " Read list", 5);
    }

    public List<WebElement> savedArticlesElements() {
        return webElementsList(
                SAVED_ARTICLES,
                ">>>> Saved articles not found",
                5
        );
    }

    public int countSavedArticlesPerPage() {
        List<WebElement> savedArticles = webElementsList(
                SAVED_ARTICLES,
                ">>>> Saved articles not found (error while counting...)",
                5
        );
        System.out.println("Count of saved articles: " + savedArticles.size());
        return savedArticles.size();
    }

    public void deleteLastArticleInList(List<WebElement> savedArticles) {
        if (lib.Platform.getInstance().isAndroid()) {
            int countSavedArticles = savedArticles.size();
            WebElement articleToDelete = savedArticles.get(countSavedArticles - 1);
            String articleToDeleteTitle = articleToDelete.getAttribute("text");
            String articleToDeleteXpath = savedArticle(articleToDeleteTitle);
            System.out.println("Article to delete: " + articleToDeleteTitle);
            swipeElementToLeft(
                    articleToDeleteXpath + "/../../*[@resource-id='org.wikipedia:id/page_list_item_action_primary']",
                    ">>>> Cannot swipe to the left on element"
            );
            waitForElementPresent(SNACK_BAR, ">>>> Snackbar message not displayed");
            waitForElementNotPresent(
                    articleToDeleteXpath,
                    ">>>> Element " + "'" + articleToDeleteTitle + "'" + " still displayed",
                    10
            );
        }


    }

    public void deleteArticleByTitle(String title) {
        waitForElementAndClick(
                EDIT_BUTTON,
                ">>>> Can not find Edit Button",
                10
        );
        String locator = savedArticle(title);
        waitForElementAndClick(
                locator,
                ">>>> Can not find element by title:" + title,
                10
        );
        waitForElementAndClick(
                UNSAVE_BUTTON,
                ">>>> Can not find Unsave button",
                10
        );

    }

    public void checkSavedArticleExistsByTitle(String articleTitle) {
        String articleToCheckXpath = savedArticle(articleTitle);
        this.waitForElementPresent(
                articleToCheckXpath,
                ">>>> Article " + articleTitle + " not displayed",
                5);
    }

    public void tapOnSavedArticleByTitle(String articleTitle) {
        String articleToClickXpath = savedArticle(articleTitle);
        this.waitForElementAndClick(
                articleToClickXpath,
                ">>>> Article " + articleTitle + " not found",
                5);
    }

    public String getArticleTitle(WebElement element) {
        if (lib.Platform.getInstance().isAndroid()) {
            return element.getAttribute("text");
        } else if (lib.Platform.getInstance().isIOS()) {
            return element.getAttribute("name");
        } else
            return null;
    }
}
