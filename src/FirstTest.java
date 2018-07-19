import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/sergeyromanchikov/Documents/GitHub/AppiumAutomationTraining/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){

        WebElement searchField = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        searchField.click();

        WebElement searchLine = waitElementPresentByID(
                "org.wikipedia:id/search_src_text",
                "Element not found"
        );

        checkTextValue(
                searchLine,
                "Search…");
    }

    private WebElement waitElementPresentByXpath(String xpath, String message){
        return waitElementPresentByXpath(xpath, message, 5);
    }

    private WebElement waitElementPresentByID(String id, String message){
        return waitElementPresentByID(id, message, 5);
    }

    private WebElement waitElementPresentByXpath(String xpath, String message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitElementPresentByID(String id, String message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private static void checkTextValue(WebElement elementForCheck, String targetValue){
        String gettedTextValue = elementForCheck.getAttribute("text");
        Assert.assertEquals(
                "Mismatch values",
                targetValue,
                gettedTextValue
        );
    }
}
