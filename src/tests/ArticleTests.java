package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.ReadListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.ReadListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testAssertTitle() {
        ReadListPageObject ReadListPageObject = ReadListPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchRequest = "Apple";
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest(searchRequest);
        List<WebElement> searchResults = SearchPageObject.searchResultElements();
        WebElement element = searchResults.get(0);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            element.click();
            ArticlePageObject.assertArticleTitlePresent("");
        }
        if (Platform.getInstance().isIOS()) {
            String articleAndDescription = ReadListPageObject.getArticleTitle(element);
            String title = SearchPageObject.getTitleValueFromSearchResult(articleAndDescription);
            element.click();
            ArticlePageObject.assertArticleTitlePresent(title);
        }
    }
}
