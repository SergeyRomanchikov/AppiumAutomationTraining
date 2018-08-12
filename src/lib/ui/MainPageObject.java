package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {

        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String message) {
        return waitForElementPresent(locator, message, 5);
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public static void checkTextValueInElement(WebElement elementForCheck, String targetValue) {
        if (Platform.getInstance().isAndroid()) {
            String receivedTextValue = elementForCheck.getAttribute("text");
            Assert.assertEquals(
                    ">>>> Mismatch values",
                    targetValue,
                    receivedTextValue
            );
        } else if (Platform.getInstance().isIOS()) {
            String receivedTextValue = elementForCheck.getText();
            Assert.assertEquals(
                    ">>>> Mismatch values",
                    targetValue,
                    receivedTextValue
            );
        }


    }

    public static void checkSearchFieldContainsText() {
        if (Platform.getInstance().isIOS()) {
        }
    }

    public List<WebElement> webElementsList(String locator, String message, long timeOutInseconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public boolean webElementsListIsNotPresents(List<WebElement> elementsList, String message, long timeOutInseconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
        wait.withMessage(message + "\n");

        return wait.until(
                ExpectedConditions.invisibilityOfAllElements(elementsList)
        );
    }

    public void checkElementsInListContainsTextValue(List<WebElement> elementsList, String value, String error_message) {
        for (WebElement anElementsList : elementsList) {
            String searchResultValue = anElementsList.getAttribute("text").toLowerCase();
            Assert.assertTrue(
                    error_message,
                    searchResultValue.contains(value.toLowerCase())
            );

        }
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public WebElement waitForElementToBeClickable(WebElement element, String message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    public WebElement longTapOnElement(WebElement element, String error_message, long timeoutInSeconds) {
        WebElement el = waitForElementToBeClickable(element, error_message, timeoutInSeconds);
        el.getLocation();
        TouchAction action = new TouchAction(driver);
        int x = el.getLocation().x;
        int y = el.getLocation().y;
        action.longPress(x, y).perform();
        return el;
    }

    public WebElement waitForInputElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
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

    public void swipeLeftByElement(WebElement element) {
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y);
        action.waitAction(300);

        int offset_x = (-1 * element.getSize().getWidth());
        action.moveTo(offset_x, 0);
        action.release();
        action.perform();

    }

    public void assertElementPresent(String locator, String error_message) {
        By by = getLocatorByString(locator);
        try {
            WebElement element = driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("An element '" + by.toString() + "' supposed to be present: " + error_message);
        }

    }

    public void pressBack() {
        driver.navigate().back();
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        // System.out.println(locator);
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }
}

