package lib.UI;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SavedArticlesPageObject extends MainPageObject{
    public SavedArticlesPageObject(AndroidDriver driver) {
        super(driver);
    }
    private static final String
    SAVED_LIST_NAME_XPATH = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']",
    ARTICLE_ELEMENT_ID = "org.wikipedia:id/page_list_item_title",
    ARTICLE_NAME_XPATH = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";

    public void openSavedList (String list_name) {
        waitForElementAndClick(
                By.xpath(getSavedListNameXpath(list_name)),
                "Not find " + list_name + "List",
                5
        );
    }
    public void swipeToDeleteArticle(String article_name) {
        swipe(
                By.xpath(getArticleNameXpath(article_name)),
                "Not find " + article_name + " article",
                5,
                "left",
                0.75
        );
    }
    private static String getSavedListNameXpath(String substring){
        return SAVED_LIST_NAME_XPATH.replace("{SUBSTRING}",substring);

    }
    private static String getArticleNameXpath(String substring){
        return ARTICLE_NAME_XPATH.replace("{SUBSTRING}",substring);

    }
    public List<WebElement> getSavedArticles(){
        List<WebElement> saved_articles_elements = waitForElementsPresent(
                By.id(ARTICLE_ELEMENT_ID),
                "Not find any articles",
                5);
        return saved_articles_elements;
    }
    public void assertAfterDeleteArticle(String article_title_expected){
        for (WebElement saved_articles_element : getSavedArticles()) {
            String article_title = saved_articles_element.getText();
            Assertions.assertFalse(article_title.contains(article_title_expected), article_title_expected + " article still in the list");
        }
    }
    public void openArticle(String article_title){
        waitForElementAndClick(
                By.id(getArticleNameXpath(article_title)),
                "Not find " + article_title + " article",
                5
                );
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
