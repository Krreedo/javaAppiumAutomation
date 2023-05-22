package lib.UI;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject{

    public SearchPageObject(AndroidDriver driver) {
        super(driver);
    }
    private static final String
    SEARCH_CONTAINER_ID = "org.wikipedia:id/search_container",
    SEARCH_INPUT_FIELD_ID = "org.wikipedia:id/search_src_text",
    CLOSE_SEARCH_BUTTON_ID = "org.wikipedia:id/search_close_btn",
    SEARCH_EMPTY_CONTAINER_ID = "org.wikipedia:id/search_empty_container",
    PAGE_LIST_ID = "org.wikipedia:id/page_list_item_title",
    ARTICLE_TITLE_XPATH = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";
public void searchText(String value){
        waitForElementAndClick(
                By.id(SEARCH_CONTAINER_ID),
                "Not find search container on the page",
                5
        );
        waitForElementAndSendKeys(
                By.id(SEARCH_INPUT_FIELD_ID),
                "Not find input field",
                value,
                5
        );

}
public void searchResultsExist(){
    for (WebElement searchResult : getSearchResultsList()){
        Assertions.assertTrue(searchResult.isDisplayed(),"Titles not displayed");
    }

}
public void closeSearch(){
   waitForElementAndClick(
            By.id(CLOSE_SEARCH_BUTTON_ID),
            "Not find Close button",
            5
    );

}
public void searchResultsIsEmpty(){
    waitForElementPresent(
            By.id(SEARCH_EMPTY_CONTAINER_ID),
            "Search is not empty",
            5
    );
}
public List<WebElement> getSearchResultsList(){
        List<WebElement> articles_elements = waitForElementsPresent(By.id(PAGE_LIST_ID),
        "Not find any articles",
        5);
    return articles_elements;
}
public void assertArticleTitles(String search_word){
    for (WebElement articles_element : getSearchResultsList()) {
        String article_title = articles_element.getText();
        Assertions.assertTrue(article_title.contains(search_word), "Not all articles contains search-word " + search_word + " in title");
    }
}
private static String getSearchResultXpath(String substring){
    return ARTICLE_TITLE_XPATH.replace("{SUBSTRING}",substring);

}
public void openArticle(String article_title){
    waitForElementAndClick(
            By.xpath(getSearchResultXpath(article_title)),
            "Not find " + article_title + " article",
            5
    );
}
}