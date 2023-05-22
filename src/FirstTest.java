import lib.CoreTestCase;
import lib.UI.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends CoreTestCase {



    @Test
    public void testInputFieldContainsText() {
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.skipOnboarding();
        MainPageObject.assertElementHasText(
                By.xpath("//androidx.cardview.widget.CardView/android.widget.TextView"),
                "Search Wikipedia",
                "Search bar text is invalid",
                5
        );
    }

    @Test
    public void testCancelSearch() {
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_word = "Tears";
        SearchPageObject.searchText(search_word);
        SearchPageObject.searchResultsExist();
        SearchPageObject.closeSearch();
        SearchPageObject.searchResultsIsEmpty();
    }

    @Test
    public void testAssertTitlesFromSearch() {
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_word = "Tears";
        SearchPageObject.searchText(search_word);
        int number_of_articles_on_screen = 6;
        Assertions.assertEquals(number_of_articles_on_screen, SearchPageObject.getSearchResultsList().size());
        SearchPageObject.assertArticleTitles(search_word);
    }

    @Test
    public void testTwoArticlesSave() {
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_word = "Thor";
        SearchPageObject.searchText(search_word);
        String article_title_1 = "Thor: Love and Thunder";
        SearchPageObject.openArticle(article_title_1);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String list_name = "MCU Films";
        ArticlePageObject.firstSaveArticleToList(list_name);
        driver.navigate().back();
        String article_title_2 = "Thor: Ragnarok";
        SearchPageObject.openArticle(article_title_2);
        ArticlePageObject.saveArticleToList(list_name);
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        NavigationPageObject NavigationPageObject = new NavigationPageObject(driver);
        NavigationPageObject.savedPage();
        SavedArticlesPageObject SavedArticlesPageObject = new SavedArticlesPageObject(driver);
        SavedArticlesPageObject.openSavedList(list_name);
        SavedArticlesPageObject.swipeToDeleteArticle(article_title_1);
        SavedArticlesPageObject.assertAfterDeleteArticle(article_title_1);
        SavedArticlesPageObject.openArticle(article_title_2);
        ArticlePageObject.assertArticleHasTitle(article_title_2);


    }
    @Test
    public void testFindingArticleTitle(){
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_word = "Thor";
        SearchPageObject.searchText(search_word);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Thor: Love and Thunder']"),
                "Not find Love Ant Thunder article",
                5
        );
        MainPageObject.assertElementPresent(
                By.xpath("//android.view.View/android.view.View[1]/android.view.View[1][@text='Thor: Love and Thunder']"),
                "Not Find Article title"
        );

    }

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



}
