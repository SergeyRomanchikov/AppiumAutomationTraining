package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testAssertTitle() {

        String searchRequest = "Appium";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.assertSearchLinePresent();
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest(searchRequest);
        SearchPageObject.clickOnRandomSearchResult();

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertArticleTitlePresent();
    }
}
