package test.java;

import lib.CoreTestCase;
import lib.UI.Factories.OnBoardingPageObjectFactory;
import lib.UI.Factories.SearchPageObjectFactory;
import lib.UI.OnBoardingPageObject;
import lib.UI.SearchPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testInputFieldContainsText() {
        OnBoardingPageObject OnBoardingPageObject = OnBoardingPageObjectFactory.get(driver);
        OnBoardingPageObject.skipOnboarding();
        MainPageObject.assertElementHasText(
                "xpath://androidx.cardview.widget.CardView/android.widget.TextView",
                "Search Wikipedia",
                "Search bar text is invalid",
                5
        );
    }

    @Test
    public void testCancelSearch() {
        OnBoardingPageObject OnBoardingPageObject = OnBoardingPageObjectFactory.get(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String search_word = "Tears";
        SearchPageObject.searchText(search_word);
        SearchPageObject.searchResultsExist();
        SearchPageObject.clearSearch();
        SearchPageObject.searchResultsIsEmpty();
    }

    @Test
    public void testAssertTitlesFromSearch() {
        OnBoardingPageObject OnBoardingPageObject = OnBoardingPageObjectFactory.get(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String search_word = "Tears";
        SearchPageObject.searchText(search_word);
        int number_of_articles_on_screen = 6;
        Assertions.assertEquals(number_of_articles_on_screen, SearchPageObject.getSearchResultsList().size());
        SearchPageObject.assertArticleTitles(search_word);
    }

    @Test
    public void testSearchResultsCorrect() {
        OnBoardingPageObject OnBoardingPageObject = OnBoardingPageObjectFactory.get(driver);
        OnBoardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String search_word = "Thor";
        SearchPageObject.searchText(search_word);
        List<String> expected_titles = Arrays.asList("Thor", "Thor: Love and Thunder", "Thor: The Dark World");
        List<String> expected_descriptions = Arrays.asList("Hammer-wielding Germanic god associated with thunder", "2022 Marvel Studios film", "2013 Marvel Studios film");
        SearchPageObject.assertTitlesAndDescriptionsInSearchResult(expected_titles, expected_descriptions);
    }
}
