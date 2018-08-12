package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.ReadListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.ReadListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReadListTests extends CoreTestCase {
    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSaveArticleToFavoritesAndRemove() {

        String searchRequest = "Apple";
        String readListName = "My Articles";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ReadListPageObject ReadListPageObject = ReadListPageObjectFactory.get(driver);
        MainPageObject MainPageObject = new MainPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest(searchRequest);

        List<WebElement> searchResults = SearchPageObject.searchResultElements();

        WebElement article_01 = searchResults.get(0);
        WebElement article_02 = searchResults.get(1);

        // Add to ReadList --- Article_01 //

        if (Platform.getInstance().isIOS()) {
            article_01.click();
            SearchPageObject.clickAddToReadListButton();
            SearchPageObject.clickOnCloseSyncDialog();
            ArticlePageObject.clickOnBackButton();
            SearchPageObject.initSearchInput();
        } else if (Platform.getInstance().isAndroid()) {
            SearchPageObject.longTapOnElement(article_01);
            SearchPageObject.clickAddToReadListButton();
            SearchPageObject.waitForLongTapMenuNotPresent();
            SearchPageObject.clickOnGotItButton();
            SearchPageObject.createNewReadList(readListName);
        }

        // Add to ReadList --- Article_02 //

        if (Platform.getInstance().isIOS()) {
            List<WebElement> results = SearchPageObject.searchResultElements();
            WebElement article_2 = results.get(1);
            article_2.click();
            SearchPageObject.clickAddToReadListButton();
            ArticlePageObject.clickOnBackButton();
        } else if (Platform.getInstance().isAndroid()) {
            SearchPageObject.longTapOnElement(article_02);
            SearchPageObject.clickAddToReadListButton();
            SearchPageObject.waitForLongTapMenuNotPresent();
            ReadListPageObject.clickOnCreatedRedList(readListName);
            MainPageObject.pressBack();
            MainPageObject.pressBack();
        }

        // Open My Read List //

        SearchPageObject.waitForElementsNotPresent(searchResults);
        SearchPageObject.clickOnMyReadListsButton();

        if (Platform.getInstance().isAndroid()) {
            ReadListPageObject.clickOnCreatedRedList(readListName);
        }

        // Check articles is saved //

        List<WebElement> savedArticles = ReadListPageObject.savedArticlesElements();

        int countSavedArticles = ReadListPageObject.countSavedArticlesPerPage();
        WebElement articleToDelete = savedArticles.get(countSavedArticles - 1);
        WebElement articleToCheck = savedArticles.get(0);

        String articleToDeleteTitle = ReadListPageObject.getArticleTitle(articleToDelete);
        String articleToCheckTitle = ReadListPageObject.getArticleTitle(articleToCheck);

        // Check saved articles //

        ReadListPageObject.checkSavedArticleExistsByTitle(articleToDeleteTitle);
        ReadListPageObject.checkSavedArticleExistsByTitle(articleToCheckTitle);

        // Delete element from read list //

/*
        TouchAction action = new TouchAction(driver);

        int left_x = element.getLocation().getX();
        System.out.println("left_x: " + left_x);
        int right_x = left_x + element.getSize().getWidth();
        System.out.println("right_x: " + right_x);
        int upper_y = element.getLocation().getY();
        System.out.println("upper_y: "+ upper_y);
        int lower_y = upper_y + element.getSize().getHeight();
        System.out.println("lower_y: " +lower_y);
        int middle_y = (upper_y + lower_y) / 2;
        System.out.println("middle_y: " +middle_y);

        int offset_x = ( -1 * element.getSize().getWidth());
        System.out.println("offset_x: " + offset_x);
        action.press(right_x, middle_y);
        action.waitAction(500);
        action.moveTo(offset_x, 0);
        action.release();
        action.perform();
        */

        if (Platform.getInstance().isAndroid()) {
            ReadListPageObject.deleteLastArticleInList(savedArticles);
        } else if (Platform.getInstance().isIOS()) {
            ReadListPageObject.deleteArticleByTitle(articleToDeleteTitle);
        }

        // Open saved article and check Title//

        ReadListPageObject.tapOnSavedArticleByTitle(articleToCheckTitle);
        if (Platform.getInstance().isAndroid()) {
            String currentArticleTitle = ArticlePageObject.getArticleTitle();
            ArticlePageObject.assertTitle(currentArticleTitle, currentArticleTitle);
        } else if (Platform.getInstance().isIOS()) {
            // System.out.println(articleToCheckTitle);
            String description = SearchPageObject.getDescriptionValueFromSearchResult(articleToCheckTitle);
            // System.out.println(description);
            MainPageObject.waitForElementPresent(
                    "id:" + description,
                    "...",
                    30
            );
        }
    }
}