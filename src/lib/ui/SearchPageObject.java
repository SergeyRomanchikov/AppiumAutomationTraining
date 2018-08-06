package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SearchPageObject extends MainPageObject {

    private static final String
            MY_READ_LIST_BUTTON = "//*[contains(@content-desc, 'My lists')]",
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_LINE_ELEMENT = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ARTICLE_ID = "org.wikipedia:id/page_list_item_title",
            ADD_TO_READLIST_BUTTON = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]",
            GOT_IT_BUTTON = "org.wikipedia:id/onboarding_button",
            INPUT_READ_LIST_NAME = "org.wikipedia:id/text_input",
            DIALOG_OK_BUTTON = "android:id/button1";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput(){
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Cannot find input after click search init element, 5");
    }

    public void inputSearchRequest(String searchRequest){
        this.waitForElementAndSendKeys(By.id(SEARCH_LINE_ELEMENT), searchRequest, ">>>> Search input not found", 5);
    }

    public WebElement searchLineElement(){
        return waitForElementPresent(By.id(SEARCH_LINE_ELEMENT), "Cannot find search line element", 5);
    }

    public void clickCancelSearchButton(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), ">>>> Search cancel button not found", 5);
    }

    public List<WebElement> searchResultElements(){
        return webElementsList(
                By.id(SEARCH_RESULT_ARTICLE_ID),
                ">>>> Search results not found",
                15
        );
    }

    public int countSearchResultsPerPage() {
        List<WebElement> searchResults = webElementsList(
                By.id(SEARCH_RESULT_ARTICLE_ID),
                ">>>> Search results not found",
                15
        );
        System.out.println("Results per page: " + searchResults.size());
        return searchResults.size();
    }

    public void waitForElementsNotPresent(List<WebElement> elementsList){
        webElementsListIsNotPresents(elementsList, "Search results still on the page", 5);
    }

    public void checkElementsInListContainsTextValuePO(List<WebElement> elementsList, String value){
        for (WebElement anElementsList : elementsList) {
            String searchResultValue = anElementsList.getAttribute("text").toLowerCase();
            Assert.assertTrue(
                    ">>>> Search result does not contain search request value",
                    searchResultValue.contains(value.toLowerCase())
            );

        }
    }

    public void assertSearchLinePresent(){
        this.assertElementPresent(By.id(SEARCH_INIT_ELEMENT), ">>>> Search container not found");
    }

    public void clickOnRandomSearchResult(){

        List<WebElement> searchResults = this.searchResultElements();
        int countResults = this.countSearchResultsPerPage();
        int articleNumber = getRandomNumberInRange(0, countResults - 1);
        WebElement randomArticle = searchResults.get(articleNumber);
        randomArticle.click();
    }

    public void longTapOnElement(WebElement element){
        this.longTapOnElement(element, ">>>> Error while long-tapping on element '" + element +"'", 5);
    }

    public void clickAddToReadListButton(){
        this.waitForElementAndClick(By.xpath(ADD_TO_READLIST_BUTTON), ">>>> 'Add to reading list' button not found", 5);
    }

    public void waitForLongTapMenuNotPresent(){
        this.waitForElementNotPresent(By.xpath(ADD_TO_READLIST_BUTTON), ">>>> 'Add to reading list' button still displayed", 5);
    }

    public void clickOnGotItButton(){
        this.waitForElementAndClick(By.id(GOT_IT_BUTTON), ">>>> 'Got It' button not found", 5);
    }

    public void createNewReadList(String readListName){

        this.waitForInputElementAndClear(By.id(INPUT_READ_LIST_NAME), ">>>> Input field not found (clear operation)", 5);
        this.waitForElementAndSendKeys(By.id(INPUT_READ_LIST_NAME), readListName,">>>> Input field not found (sendKeys operation)", 5);
        this.waitForElementAndClick(By.id(DIALOG_OK_BUTTON), ">>>>  'Ok' button not found" , 5);
        this.waitForElementNotPresent(By.id(DIALOG_OK_BUTTON), ">>>>  'Ok' button still displayed", 5);
    }

    public void clickOnMyReadListsButton(){
        this.waitForElementAndClick(By.xpath(MY_READ_LIST_BUTTON), ">>>> 'My Lists' button not found", 5);
        this.waitForElementNotPresent(By.id("org.wikipedia:id/single_fragment_toolbar_wordmark"), ">>>> Home page still displayed", 5);
    }




}
