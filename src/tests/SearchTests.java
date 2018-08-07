package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lib.ui.MainPageObject.checkTextValueInElement;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearchLineTextValue() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        checkTextValueInElement(SearchPageObject.searchLineElement(), "Search…");

    }

    @Test
    public void testSearchResultsCountAndCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest("Java");
        List<WebElement> searchResultElements = SearchPageObject.searchResultElements();
        assertTrue(
                ">>>> Multiple results not found",
                searchResultElements.size() > 1
        );
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.waitForElementsNotPresent(searchResultElements);

    }

    @Test
    public void testSearchAndCheckSearchResults() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String searchRequest = "Apple";
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest(searchRequest);
        SearchPageObject.checkElementsInListContainsTextValuePO(SearchPageObject.searchResultElements(), searchRequest);

    }

}
