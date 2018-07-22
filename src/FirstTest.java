import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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

    public void searchResultsCountAndCancelSearch(){

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

    private List<WebElement> webElementsList(By by, String message, long timeOutInseconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private boolean webElementsListIsNotPresents(List<WebElement> elementsList, String message, long timeOutInseconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
        wait.withMessage(message + "\n");

        return wait.until(
                ExpectedConditions.invisibilityOfAllElements(elementsList)
        );
    }
}


