package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
import lib.UI.Factories.*;
import org.junit.jupiter.api.Test;

public class SavedArticlesTests extends CoreTestCase {
    @Test
    public void testTwoArticlesSave() {
        OnBoardingPageObject OnBoardingPageObject = OnBoardingPageObjectFactory.get(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String search_word = "Thor";
        SearchPageObject.searchText(search_word);
        String article_title_1 = "Thor: Love and Thunder";
        String article_title_2 = "Thor: Ragnarok";
        SearchPageObject.openArticle(article_title_1);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String list_name = "MCU Films";
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.firstSaveArticleToList(list_name);
            driver.navigate().back();
            SearchPageObject.openArticle(article_title_2);
            ArticlePageObject.saveArticleToList(list_name);
            driver.navigate().back();
            driver.navigate().back();
            driver.navigate().back();
        } else {
            ArticlePageObject.saveFirstArticleToListIOS(list_name, article_title_1);
            driver.navigate().back();
            SearchPageObject.openArticle(article_title_2);
            ArticlePageObject.saveArticleToListIOS(list_name, article_title_2);
            driver.navigate().back();
            SearchPageObject.closeSearch();
        }

        NavigationPageObject NavigationPageObject = NavigationPageObjectFactory.get(driver);
        NavigationPageObject.savedPage();
        SavedArticlesPageObject SavedArticlesPageObject = SavedArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            SavedArticlesPageObject.closePopUp();
            SavedArticlesPageObject.chooseReadingLists();
        }
        SavedArticlesPageObject.openSavedList(list_name);
        SavedArticlesPageObject.swipeToDeleteArticle(article_title_1);
        SavedArticlesPageObject.assertAfterDeleteArticle(article_title_1);
        SavedArticlesPageObject.openArticle(article_title_2);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.assertArticleHasTitle(article_title_2);
        } else {
            ArticlePageObject.assertArticleTitleInTableOfContents(article_title_2);
        }


    }
}
