package lib.UI;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageObject {
    public AndroidDriver driver;

    public MainPageObject(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement assertElementHasText(By by, String expected_text, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        String actual_text = element.getText();
        Assertions.assertEquals(expected_text, actual_text, error_message);
        return element;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }

    public List<WebElement> waitForElementsPresent(By by, String error_message, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String error_message, String value, long timeOut) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        element.sendKeys(value);
        return element;
    }

    public void swipe(By by, String error_message, long timeOut, String direction, double percent) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int left = location.getX();
        int top = location.getY();
        int height = size.getHeight();
        int width = size.getWidth();
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", left, "top", top, "width", width, "height", height,
                "direction", direction,
                "percent", percent,
                "speed", 3000
        ));
    }

    public WebElement assertElementPresent(By by, String error_message) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            WebElement element = driver.findElement(by);
            return element;
        } catch (NoSuchElementException e) {
            throw new AssertionError(error_message);
        }


    }
}
