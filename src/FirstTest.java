import lib.CoreTestCase;
import lib.UI.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
    public void testFindingArticleTitle() {
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_word = "Thor";
        SearchPageObject.searchText(search_word);
        String article_title = "Thor: Love and Thunder";
        SearchPageObject.openArticle(article_title);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertArticleHasTitle(article_title);

    }

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


}
