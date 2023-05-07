import graphql.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;


    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/oleg.sofronov/Documents/JavaAppiumAutomation/javaAppiumAutomation/apks/Wikipedia_2.7.50437-r-2023-04-12_Apkpure.apk");
        capabilities.setCapability("automationName", "UiAutomator2");
        URL url = new URL("http://localhost:4723/");

        driver = new AndroidDriver(url, capabilities);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void inputFieldContainsText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Not find Skip button",
                5
        );
        assertElementHasText(
                By.xpath("//androidx.cardview.widget.CardView/android.widget.TextView"),
                "Search Wikipedia",
                "Search bar text is invalid",
                5
        );
    }

    @Test
    public void cancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Not find Skip button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Not find SearchBar",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Not find SearchBar",
                5
        ).sendKeys("Tears");
        List<WebElement> articles_elements = waitForElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Not find any articles",
                5);
        Assertions.assertEquals(6, articles_elements.size());
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Not find Close button",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_container"),
                "Search is not empty",
                5
        );


    }

    private WebElement assertElementHasText(By by, String expected_text, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        String actual_text = element.getText();
        Assertions.assertEquals(expected_text, actual_text, error_message);
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }

    private List<WebElement> waitForElementsPresent(By by, String error_message, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        element.click();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return element;
    }


}
