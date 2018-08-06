package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {

        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String message) {
        return waitForElementPresent(by, message, 5);
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public static void checkTextValueInElement(WebElement elementForCheck, String targetValue) {
        String receivedTextValue = elementForCheck.getAttribute("text");
        Assert.assertEquals(
                ">>>> Mismatch values",
                targetValue,
                receivedTextValue
        );
    }

    public List<WebElement> webElementsList(By by, String message, long timeOutInseconds) {
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

    public WebElement waitForInputElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeElementToLeft(By by, String error_message) {
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

    public void assertElementPresent(By by, String error_message) {
        try {
            WebElement element = driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("An element '" + by.toString() + "' supposed to be present: " + error_message);
        }

    }

    public void pressBack() {
        driver.navigate().back();
    }
}
