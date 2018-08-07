package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.ReadListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lib.ui.MainPageObject.getRandomNumberInRange;

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

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest(searchRequest);

        List<WebElement> searchResults = SearchPageObject.searchResultElements();

        int countResults = SearchPageObject.countSearchResultsPerPage();

        // Select two random search results //

        int article_01_number = getRandomNumberInRange(0, countResults - 1);
        int article_02_number = getRandomNumberInRange(0, countResults - 1);
        while (article_01_number == article_02_number) {
            article_02_number = getRandomNumberInRange(0, countResults);
        }

        WebElement article_01 = searchResults.get(article_01_number);
        WebElement article_02 = searchResults.get(article_02_number);

        // Add to ReadList --- Article_01 //

        SearchPageObject.longTapOnElement(article_01);
        SearchPageObject.clickAddToReadListButton();
        SearchPageObject.waitForLongTapMenuNotPresent();
        SearchPageObject.clickOnGotItButton();
        SearchPageObject.createNewReadList(readListName);

        // Add to ReadList --- Article_02 //

        SearchPageObject.longTapOnElement(article_02);
        SearchPageObject.clickAddToReadListButton();
        SearchPageObject.waitForLongTapMenuNotPresent();
        ReadListPageObject ReadListPageObject = new ReadListPageObject(driver);
        ReadListPageObject.clickOnCreatedRedList(readListName);

        // Back to home page //

        MainPageObject.pressBack();
        MainPageObject.pressBack();

        // Open My Read List //

        SearchPageObject.waitForElementsNotPresent(searchResults);
        SearchPageObject.clickOnMyReadListsButton();
        ReadListPageObject.clickOnCreatedRedList(readListName);

        // Check articles is saved //

        List<WebElement> savedArticles = ReadListPageObject.savedArticlesElements();

        int countSavedArticles = ReadListPageObject.countSavedArticlesPerPage();
        WebElement articleToDelete = savedArticles.get(countSavedArticles - 1);
        WebElement articleToCheck = savedArticles.get(0);
        String articleToDeleteTitle = articleToDelete.getAttribute("text");
        String articleToCheckTitle = articleToCheck.getAttribute("text");

        // Check saved articles //

        ReadListPageObject.checkSavedArticleExistsByTitle(articleToDeleteTitle);
        ReadListPageObject.checkSavedArticleExistsByTitle(articleToCheckTitle);

        // Delete element from read list //

        ReadListPageObject.deleteLastArticleInList(savedArticles);

        // Open saved article and check Title//

        ReadListPageObject.tapOnSavedArticleByTitle(articleToCheckTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String currentArticleTitle = ArticlePageObject.getArticleTitle();
        ArticlePageObject.assertTitle(currentArticleTitle, currentArticleTitle);


    }
}
