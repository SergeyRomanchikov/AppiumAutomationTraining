package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.applet.Main;

import java.util.List;

import static lib.ui.MainPageObject.checkTextValueInElement;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearchLineTextValue() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        checkTextValueInElement(SearchPageObject.searchLineElement(), "Search…");

    }

    @Test
    public void testSearchResultsCountAndCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

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

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String searchRequest = "Apple";
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest(searchRequest);
        SearchPageObject.checkElementsInListContainsTextValuePO(SearchPageObject.searchResultElements(), searchRequest);

    }


    @Test
    public void testWaitForArticlesByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest("Marvel");

        SearchPageObject.waitForElementByTitleAndDescription("Marvel", "Wikimedia disambiguation page");
        SearchPageObject.waitForElementByTitleAndDescription("Marvel Cinematic Universe", "Film franchise and shared fictional universe");
        SearchPageObject.waitForElementByTitleAndDescription("Marvel Comics", "Company that publishes comic books and related media");

    }

    @Test
    public void testTemp(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        MainPageObject MainPageObject = new MainPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchRequest("Apple");

        WebElement temp = SearchPageObject.waitForElementPresent(
                "xpath://XCUIElementTypeCell",
                "...",
                15
        );

        List<WebElement> list = MainPageObject.webElementsList("xpath://XCUIElementTypeCell",
                "...",
                15);
        System.out.println(list);
        System.out.println(list.size());


        for (WebElement element : list) {
            String name = element.getAttribute("label");
            System.out.println(name);
        }





    }
}
