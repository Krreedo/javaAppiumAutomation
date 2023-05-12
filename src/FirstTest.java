import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FirstTest {
    private AndroidDriver driver;


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
        capabilities.setCapability("deviceOrientation", "Portrait");
        URL url = new URL("http://localhost:9090/");

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

    @Test
    public void assertTitlesFromSearch() {
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
        String search_word = "Tears";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Not find SearchBar",
                search_word,
                5
        );
        List<WebElement> articles_elements = waitForElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Not find any articles",
                5);
        for (WebElement articles_element : articles_elements) {
            String article_title = articles_element.getText();
            Assertions.assertTrue(article_title.contains(search_word), "Not all articles contains search-word " + article_title + " in title");
        }
    }

    @Test
    public void twoArticlesSave() {
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
        String search_word = "Thor";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Not find SearchBar",
                search_word,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Thor: Love and Thunder']"),
                "Not find Love Ant Thunder article",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Not find Add to List Button",
                5
        );
        String list_name = "MCU Films";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Not find text field",
                list_name,
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1'][@text='OK']"),
                "Not find OK Button",
                15
        );
        driver.navigate().back();
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Thor: Ragnarok']"),
                "Not find Ragnarok article",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Not find Add to List Button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title_container"),
                "Not find Add to List Button",
                5
        );
        waitForElementsPresent(
                By.id("org.wikipedia:id/fragment_page_coordinator"),
                "Snackbar not presented after article save",
                5
        );
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Not find Saved Button in tab-bar",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + list_name + "']"),
                "Not find MCU List",
                5
        );
        swipe(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Thor: Love and Thunder']"),
                "Not find article 'Thor: Love and Thunder'",
                5,
                "left",
                0.75
        );

//        driver.executeScript(
//                "mobile: longClick",
//                "{element: "+ waitForElementPresent(
//                        By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text=Thor: Love and Thunder]"),
//                        "Not find Love and Thunder article",
//                        5
//                ) + "}"
//        );
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/reading_list_item_select"),
//                "Not find Select Button in bottom-sheet",
//                5
//        );
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/menu_delete_selected"),
//                "Not find Delete button",
//                5
//        );
        List<WebElement> saved_articles_elements = waitForElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "Not find any articles",
                5);
        for (WebElement saved_articles_element : saved_articles_elements) {
            String article_title = saved_articles_element.getText();
            Assertions.assertTrue(article_title.contains("Thor: Ragnarok"), "There is more then one article with " + article_title + " in title");
        }
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Thor: Ragnarok']"),
                "Not find article Thor: Ragnarok",
                5
        );
        String article_title = waitForElementPresent(
                By.xpath("//android.view.View/android.view.View[1]/android.view.View[1]"),
                "Not find title of article",
                5
        ).getText();
        Assertions.assertEquals(
                "Thor: Ragnarok",
                article_title,
                "Wrong article title"
        );


    }
    @Test
    public void findingArticleTitle(){
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
        String search_word = "Thor";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Not find SearchBar",
                search_word,
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Thor: Love and Thunder']"),
                "Not find Love Ant Thunder article",
                5
        );
        assertElementPresent(
                By.xpath("//android.view.View/android.view.View[1]/android.view.View[1][@text='Thor: Love and Thunder']"),
                "Not Find Article title"
        );

    }

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


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
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String error_message, String value, long timeOut) {
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
    private WebElement assertElementPresent(By by, String error_message) {
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
