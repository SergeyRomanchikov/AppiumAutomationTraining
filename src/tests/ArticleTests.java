package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testAssertTitle() {

        String searchRequest = "Appium";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.assertSearchLinePresent();
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest(searchRequest);
        SearchPageObject.clickOnRandomSearchResult();

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertArticleTitlePresent();
    }
}
