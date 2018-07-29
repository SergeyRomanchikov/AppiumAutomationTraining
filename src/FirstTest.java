import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.Random;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/sergeyromanchikov/Documents/GitHub/AppiumAutomationTraining/apks/org.wikipedia.apk");
        capabilities.setCapability("orientation", "PORTRAIT");
        // capabilities.setCapability("fullReset", "true");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                ">>>> Search container not found",
                5
        );

        WebElement searchLine = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                ">>>> Element not found"
        );

        checkTextValue(
                searchLine,
                "Search…");
    }

    @Test
    public void searchResultsCountAndCancelSearch() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                ">>>> Search container not found",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                ">>>> Search input not found",
                5
        );

        List<WebElement> searchResults = webElementsList(
                By.id("org.wikipedia:id/page_list_item_title"),
                ">>>> Search results not found",
                15
        );

        int countResults = searchResults.size();
        System.out.println("Number of results: " + countResults);

        Assert.assertTrue(
                ">>>> Multiple results not found",
                countResults > 1
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                ">>>> Search close button not found",
                5
        );

        webElementsListIsNotPresents(
                searchResults,
                ">>>> Search results still presents on the page",
                5
        );
    }

    @Test
    public void searchAndCheckSearchResults() {

        String searchRequest = "Apple";
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                ">>>> Search container not found",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchRequest,
                ">>>> Search input not found",
                5
        );

        List<WebElement> searchResults = webElementsList(
                By.id("org.wikipedia:id/page_list_item_title"),
                ">>>> Search results not found",
                15
        );

        checkElementsInListContainsTextValue(
                searchResults,
                searchRequest,
                ">>>> Search result does not contain search request value"
        );
    }

    @Test
    public void saveArticleToFavoritesAndRemove() {

        String searchRequest = "Apple";
        //String addToReadListButtonXPath = "//*[contains(@text, 'Add to reading list')]";
        String addToReadListButtonXPath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]";

        String gotItButtonID = "org.wikipedia:id/onboarding_button";
        String inputReadListNameID = "org.wikipedia:id/text_input";
        String okButton = "android:id/button1";
        String readListName = "Articles by request: " + searchRequest;
        String createdReadListXPath = "//*[contains(@text, '" + readListName + "')]";
        String myReadListButtonXPath = "//*[contains(@content-desc, 'My lists')]";

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                ">>>> Search container not found",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchRequest,
                ">>>> Search input not found",
                5
        );

        List<WebElement> searchResults = webElementsList(
                By.id("org.wikipedia:id/page_list_item_title"),
                ">>>> Search results not found",
                15
        );

        int countResults = searchResults.size();
        System.out.println("Results per page: " + countResults);

        int article_01_number = getRandomNumberInRange(0, countResults - 1);
        int article_02_number = getRandomNumberInRange(0, countResults - 1);
        while (article_01_number == article_02_number) {
            article_02_number = getRandomNumberInRange(0, countResults);
        }

        WebElement article_01 = searchResults.get(article_01_number);
        WebElement article_02 = searchResults.get(article_02_number);

        // Add to ReadList --- Article_01 //

        longTapOnElement(
                article_01,
                ">>>> Error while long-tapping on article 01",
                5
        );

        waitForElementAndClick(
                By.xpath(addToReadListButtonXPath),
                ">>>> 'Add to reading list' button not found",
                5
        );

        waitForElementNotPresent(
                By.xpath(addToReadListButtonXPath),
                ">>>> 'Add to reading list' button still displayed",
                5
        );

        waitForElementAndClick(
                By.id(gotItButtonID),
                ">>>> 'Got It' button not found",
                5
        );

        waitForInputElementAndClear(
                By.id(inputReadListNameID),
                ">>>> Input field not found (clear operation)",
                4
        );

        waitForElementAndSendKeys(
                By.id(inputReadListNameID),
                readListName,
                ">>>>  Input field not found (sendKeys operation)",
                5
        );

        waitForElementAndClick(
                By.id(okButton),
                ">>>>  'Ok' button not found",
                5
        );

        waitForElementNotPresent(
                By.id(okButton),
                ">>>>  'Ok' button still displayed",
                5
        );

        // Add to ReadList --- Article_02 //

        longTapOnElement(
                article_02,
                ">>>> Error while long-tap on article 01",
                5
        );

        waitForElementAndClick(
                By.xpath(addToReadListButtonXPath),
                ">>>> 'Add to reading list' button not found",
                5
        );

        waitForElementNotPresent(
                By.xpath(addToReadListButtonXPath),
                ">>>> 'Add to reading list' button still displayed",
                5
        );

        waitForElementAndClick(
                By.xpath(createdReadListXPath),
                "Cannot find " + readListName + " Read list",
                5
        );

        // Back to home page //

        driver.navigate().back();
        driver.navigate().back();

        // Open My Read List //

        webElementsListIsNotPresents(
                searchResults,
                ">>>> Search results still displayed",
                5
        );

        waitForElementAndClick(
                By.xpath(myReadListButtonXPath),
                ">>>> 'My Lists' button not found",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/single_fragment_toolbar_wordmark"),
                ">>>> Home page still displayed",
                5
        );

        waitForElementAndClick(
                By.xpath(createdReadListXPath),
                "Cannot find " + readListName + " Read list",
                5
        );

        // Check articles is saved //

        List<WebElement> savedArticles = webElementsList(
                By.id("org.wikipedia:id/page_list_item_title"),
                ">>>> Saved articles not found",
                5
        );

        int countSavedArticles = savedArticles.size();
        System.out.println("Count of saved articles: " + countSavedArticles);
        WebElement articleToDelete = savedArticles.get(countSavedArticles - 1);
        WebElement articleToCheck = savedArticles.get(0);
        String articleToDeleteTitle = articleToDelete.getAttribute("text");
        String articleToCheckTitle = articleToCheck.getAttribute("text");

        System.out.println("Article to delete: " + articleToDeleteTitle);
        System.out.println("Article to check: " + articleToCheckTitle);


        String articleToDeleteXpath = "//*[contains(@text, '" + articleToDeleteTitle + "')]";
        String articleToCheckXpath = "//*[contains(@text, '" + articleToCheckTitle + "')]";

        waitForElementPresent(
                By.xpath(articleToDeleteXpath),
                ">>>> Article 01 not displayed",
                5
        );

        waitForElementPresent(
                By.xpath(articleToCheckXpath),
                ">>>> Article 01 not displayed",
                5
        );

        // Delete element from read list //


        swipeElementToLeft(
                By.xpath(articleToDeleteXpath + "/../../*[@resource-id='org.wikipedia:id/page_list_item_action_primary']"),
                ">>>> Cannot swipe to the left on element"
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/snackbar_text"),
                ">>>> Snackbar message not displayed"
        );

        waitForElementNotPresent(
                By.xpath(articleToDeleteXpath),
                ">>>> Element " + "'" + articleToDeleteTitle + "'" + " still displayed",
                10
        );

        // Open saved article //

        waitForElementAndClick(
                By.xpath(articleToCheckXpath),
                ">>>> Element " + "'" + articleToCheckTitle + "'" + " not found",
                5
        );

        String currentArticleTitle = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                ">>>> Error while getting the text value",
                15
        ).getAttribute("text");

        Assert.assertEquals(
                ">>>>  Title '" + currentArticleTitle + "' not equals '" + articleToCheckTitle + "'",
                articleToCheckTitle,
                currentArticleTitle);


    }

    @Test
    public void assertTitle() {

        String searchRequest = "Appium";

        assertElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Search container not found"
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                ">>>> Search container not found",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchRequest,
                ">>>> Search input not found",
                5
        );

        List<WebElement> searchResults = webElementsList(
                By.id("org.wikipedia:id/page_list_item_title"),
                ">>>> Search results not found",
                15
        );

        int countResults = searchResults.size();
        System.out.println("Results per page: " + countResults);
        int articleNumber = getRandomNumberInRange(0, countResults - 1);
        WebElement article_01 = searchResults.get(articleNumber);
        article_01.click();

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                ">>>>  Article title not found"
        );
    }


    private WebElement waitForElementPresent(By by, String message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String message) {
        return waitForElementPresent(by, message, 5);
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private static void checkTextValue(WebElement elementForCheck, String targetValue) {
        String receivedTextValue = elementForCheck.getAttribute("text");
        Assert.assertEquals(
                "Mismatch values",
                targetValue,
                receivedTextValue
        );
    }

    private List<WebElement> webElementsList(By by, String message, long timeOutInseconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private boolean webElementsListIsNotPresents(List<WebElement> elementsList, String message, long timeOutInseconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
        wait.withMessage(message + "\n");

        return wait.until(
                ExpectedConditions.invisibilityOfAllElements(elementsList)
        );
    }

    private void checkElementsInListContainsTextValue(List<WebElement> elementsList, String value, String error_message) {
        for (WebElement anElementsList : elementsList) {
            String searchResultValue = anElementsList.getAttribute("text").toLowerCase();
            Assert.assertTrue(
                    error_message,
                    searchResultValue.contains(value.toLowerCase())
            );

        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private WebElement waitForElementToBeClickable(WebElement element, String message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    private WebElement longTapOnElement(WebElement element, String error_message, long timeoutInSeconds) {
        WebElement el = waitForElementToBeClickable(element, error_message, timeoutInSeconds);
        el.getLocation();
        TouchAction action = new TouchAction(driver);
        int x = el.getLocation().x;
        int y = el.getLocation().y;
        action.longPress(x, y).perform();
        return el;
    }

    private WebElement waitForInputElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int startX = element.getLocation().getX();
        int startY = element.getLocation().getY();
        int endX = element.getLocation().getX() - (int) (element.getLocation().getX() * 0.9);
        TouchAction action = new TouchAction(driver);
        action
                .press(startX, startY).waitAction(500)
                .moveTo(endX, startY)
                .release()
                .perform();
    }

    private void assertElementPresent(By by, String error_message) {
        try {
            WebElement element = driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("An element '" + by.toString() + "' supposed to be present: " + error_message);
        }

    }

}


