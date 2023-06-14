package Tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.Factories.OnBoardingPageObjectFactory;
import lib.UI.Factories.SearchPageObjectFactory;
import lib.UI.OnBoardingPageObject;
import lib.UI.SearchPageObject;
import org.junit.jupiter.api.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testFindingArticleTitle() {
        OnBoardingPageObject OnBoardingPageObject = OnBoardingPageObjectFactory.get(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String search_word = "Thor";
        SearchPageObject.searchText(search_word);
        String article_title = "Thor: Love and Thunder";
        SearchPageObject.openArticle(article_title);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertArticleHasTitle(article_title);

    }
}
