package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SavedArticlesPageObject extends MainPageObject {
    public SavedArticlesPageObject(AppiumDriver driver) {
        super(driver);
    }

    public static String
            SAVED_LIST_NAME,
            ARTICLE_ELEMENT,
            READING_LISTS_IOS,
            SYNC_POP_UP_CLOSE_BUTTON,
            DELETE_BUTTON,
            ARTICLE_NAME;

    public void openSavedList(String list_name) {
        waitForElementAndClick(
                getSavedListNameXpath(list_name),
                "Not find " + list_name + "List",
                5
        );
    }

    public void swipeToDeleteArticle(String article_name) {
        if (Platform.getInstance().isAndroid()) {
            swipe(
                    getArticleNameXpath(article_name),
                    "Not find " + article_name + " article",
                    5,
                    "left",
                    0.75
            );
        }
        if (Platform.getInstance().isIOS()) {
            swipeIOS(
                    getArticleNameXpath(article_name),
                    "Not find " + article_name + " article",
                    5,
                    "left"
            );
            waitForElementAndClick(DELETE_BUTTON, "Not find Delete button", 10);
        }

    }

    private static String getSavedListNameXpath(String substring) {
        return SAVED_LIST_NAME.replace("{SUBSTRING}", substring);

    }

    private static String getArticleNameXpath(String substring) {
        return ARTICLE_NAME.replace("{SUBSTRING}", substring);

    }

    public List<WebElement> getSavedArticles() {
        List<WebElement> saved_articles_elements = waitForElementsPresent(
                ARTICLE_ELEMENT,
                "Not find any articles",
                5);
        return saved_articles_elements;
    }

    public void assertAfterDeleteArticle(String article_title_expected) {
        for (WebElement saved_articles_element : getSavedArticles()) {
            String article_title = saved_articles_element.getText();
            Assertions.assertFalse(article_title.contains(article_title_expected), article_title_expected + " article still in the list");
        }
    }

    public void openArticle(String article_title) {
        waitForElementAndClick(
                getArticleNameXpath(article_title),
                "Not find " + article_title + " article",
                5
        );
    }

    public void chooseReadingLists() {
        waitForElementAndClick(READING_LISTS_IOS, "Not Find Reading Lists button", 5);
    }

    public void closePopUp() {
        waitForElementAndClick(SYNC_POP_UP_CLOSE_BUTTON, "Not find pop-up close button", 5);
    }

    public void swipeLeftElement() {

    }

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
}