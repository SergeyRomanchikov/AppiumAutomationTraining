package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;


abstract public class SearchPageObject extends MainPageObject {

    protected static String
            MY_READ_LIST_BUTTON,
            SEARCH_INIT_ELEMENT,
            SEARCH_LINE_ELEMENT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ARTICLE_ID,
            ADD_TO_READLIST_BUTTON,
            GOT_IT_BUTTON,
            INPUT_READ_LIST_NAME,
            DIALOG_OK_BUTTON,
            WIKIPEDIA_LOGO_ON_MAIN_PAGE,
            SYNC_DIALOG_CLOSE_BUTTON,
            SEARCH_RESULT;


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    // TEMPLATES METHODS //

    private static String searchResultXPathByTitleAndDescription(String title, String description) {
        String editXpath = SEARCH_RESULT.replace("{ARTICLE_TITLE}", title);
        return editXpath.replace("{ARTICLE_DESCRIPTION}", description);
    }

    // TEMPLATES METHODS //

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find input after click search init element", 5);
    }

    public void inputSearchRequest(String searchRequest) {
        this.waitForElementAndSendKeys(SEARCH_LINE_ELEMENT, searchRequest, ">>>> Search input not found", 5);
    }

    public WebElement searchLineElement() {
        return waitForElementPresent(SEARCH_LINE_ELEMENT, "Cannot find search line element", 5);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, ">>>> Search cancel button not found", 5);
    }

    public List<WebElement> searchResultElements() {
        return webElementsList(
                SEARCH_RESULT_ARTICLE_ID,
                ">>>> Search results not found",
                15
        );
    }

    public int countSearchResultsPerPage() {
        List<WebElement> searchResults = webElementsList(
                SEARCH_RESULT_ARTICLE_ID,
                ">>>> Search results not found",
                15
        );
        System.out.println("Results per page: " + searchResults.size());
        return searchResults.size();
    }

    public void waitForElementsNotPresent(List<WebElement> elementsList) {
        webElementsListIsNotPresents(elementsList, "Search results still on the page", 5);
    }

    public void checkElementsInListContainsTextValuePO(List<WebElement> elementsList, String value) {
        if (Platform.getInstance().isAndroid()) {
            for (WebElement anElementsList : elementsList) {
                String searchResultValue = anElementsList.getAttribute("text").toLowerCase();
                Assert.assertTrue(
                        ">>>> Search result does not contain search request value",
                        searchResultValue.contains(value.toLowerCase()));
            }
        } else if (Platform.getInstance().isIOS()) {
            for (WebElement anElementsList : elementsList) {
                String searchResultValue = anElementsList.getAttribute("name").toLowerCase();
                Assert.assertTrue(
                        ">>>> Search result does not contain search request value",
                        searchResultValue.contains(value.toLowerCase())
                );
            }
        }
    }

    public void assertSearchLinePresent() {
        this.assertElementPresent(SEARCH_INIT_ELEMENT, ">>>> Search container not found");
    }

    public void clickOnRandomSearchResult() {

        List<WebElement> searchResults = this.searchResultElements();
        int countResults = this.countSearchResultsPerPage();
        int articleNumber = getRandomNumberInRange(0, countResults - 1);
        WebElement randomArticle = searchResults.get(articleNumber);
        randomArticle.click();
    }

    public void longTapOnElement(WebElement element) {
        this.longTapOnElement(element, ">>>> Error while long-tapping on element '" + element + "'", 5);
    }

    public void clickAddToReadListButton() {
        this.waitForElementAndClick(ADD_TO_READLIST_BUTTON, ">>>> 'Add to reading list' button not found", 5);
    }

    public void waitForLongTapMenuNotPresent() {
        this.waitForElementNotPresent(ADD_TO_READLIST_BUTTON, ">>>> 'Add to reading list' button still displayed", 5);
    }

    public void clickOnGotItButton() {
        this.waitForElementAndClick(GOT_IT_BUTTON, ">>>> 'Got It' button not found", 5);
    }

    public void createNewReadList(String readListName) {

        this.waitForInputElementAndClear(INPUT_READ_LIST_NAME, ">>>> Input field not found (clear operation)", 5);
        this.waitForElementAndSendKeys(INPUT_READ_LIST_NAME, readListName, ">>>> Input field not found (sendKeys operation)", 5);
        this.waitForElementAndClick(DIALOG_OK_BUTTON, ">>>>  'Ok' button not found", 5);
        this.waitForElementNotPresent(DIALOG_OK_BUTTON, ">>>>  'Ok' button still displayed", 5);
    }

    public void clickOnMyReadListsButton() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(MY_READ_LIST_BUTTON, ">>>> 'My Lists' button not found", 5);
            this.waitForElementNotPresent(WIKIPEDIA_LOGO_ON_MAIN_PAGE, ">>>> Home page still displayed", 5);
        } else if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(MY_READ_LIST_BUTTON, ">>>> 'My Lists' button not found", 5);
        }
    }

    public void clickOnCloseSyncDialog() {
        this.waitForElementAndClick(SYNC_DIALOG_CLOSE_BUTTON, ">>>> Can not find close dialog button", 10);
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        String finalXpath = searchResultXPathByTitleAndDescription(title, description);
        System.out.println(finalXpath);
        WebElement searchResult = this.waitForElementPresent(
                finalXpath,
                ">>>>>> Cannot find article by title: " + "'" + title + "'" + " and description: " + "'" + description + "'" + " in search results",
                15
        );
        return searchResult;
    }

    public String getDescriptionValueFromSearchResult(String titleAndDescription) {
        String[] full = titleAndDescription.split(Pattern.quote("\n"), 2);
        return full[1];
    }

    public String getTitleValueFromSearchResult(String titleAndDescription) {
        String[] full = titleAndDescription.split(Pattern.quote("\n"), 2);
        return full[0];
    }


}
