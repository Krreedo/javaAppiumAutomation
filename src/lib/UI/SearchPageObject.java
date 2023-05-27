package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SEARCH_CONTAINER_IOS = "id:Search Wikipedia",
            SEARCH_CONTAINER = "id:org.wikipedia:id/search_container",
            SEARCH_INPUT_FIELD = "id:org.wikipedia:id/search_src_text",
            CLOSE_SEARCH_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_EMPTY_CONTAINER = "id:org.wikipedia:id/search_empty_container",
            PAGE_LIST = "id:org.wikipedia:id/page_list_item_title",
            ARTICLE_TITLE_AND_DESCRIPTION = "xpath://android.view.ViewGroup/android.widget.TextView[@text='{TITLE}']/../android.widget.TextView[@text='{DESCRIPTION}']/..",
            ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";

    public void searchText(String value) {
        waitForElementAndClick(
                SEARCH_CONTAINER,
                "Not find search container on the page",
                5
        );
        waitForElementAndSendKeys(
                SEARCH_INPUT_FIELD,
                "Not find input field",
                value,
                5
        );

    }

    public void searchResultsExist() {
        for (WebElement searchResult : getSearchResultsList()) {
            Assertions.assertTrue(searchResult.isDisplayed(), "Titles not displayed");
        }

    }

    public void closeSearch() {
        waitForElementAndClick(
                CLOSE_SEARCH_BUTTON,
                "Not find Close button",
                5
        );

    }

    public void searchResultsIsEmpty() {
        waitForElementPresent(
                SEARCH_EMPTY_CONTAINER,
                "Search is not empty",
                5
        );
    }

    public List<WebElement> getSearchResultsList() {
        return waitForElementsPresent(
                PAGE_LIST,
                "Not find any articles",
                5);
    }

    public void assertArticleTitles(String search_word) {
        for (WebElement articles_element : getSearchResultsList()) {
            String article_title = articles_element.getText();
            Assertions.assertTrue(article_title.contains(search_word), "Not all articles contains search-word " + search_word + " in title");
        }
    }

    private static String getSearchResultXpath(String substring) {
        return ARTICLE_TITLE.replace("{SUBSTRING}", substring);

    }

    private static String getSearchResultXpathByTitleAndDescription(String title, String description) {
        return ARTICLE_TITLE_AND_DESCRIPTION
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);

    }

    public void openArticle(String article_title) {
        waitForElementAndClick(
                getSearchResultXpath(article_title),
                "Not find " + article_title + " article",
                5
        );
    }

    public void openArticleWithDesc(String article_title, String article_desc) {
        waitForElementAndClick(
                getSearchResultXpathByTitleAndDescription(article_title, article_desc),
                "Not find " + article_title + " article",
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        ;
        waitForElementPresent(
                getSearchResultXpathByTitleAndDescription(title, description),
                "Title '" + title + "' or description '" + description + "' doesn't match",
                5
        );


    }

    public void assertTitlesAndDescriptionsInSearchResult(List<String> titles, List<String> descriptions) {
        for (int i = 0; i < titles.size(); i++) {
            waitForElementByTitleAndDescription(titles.get(i), descriptions.get(i));
        }

    }
    public void assertSearchContainerIsPresented(){
        waitForElementPresent(SEARCH_CONTAINER_IOS,"Not find Search Field on Main Page", 15);
    }


}
