package Tests;

import lib.CoreTestCase;
import lib.UI.*;
import org.junit.jupiter.api.Test;

public class SavedArticlesTests extends CoreTestCase {
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
}
