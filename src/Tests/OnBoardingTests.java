package Tests;

import lib.CoreTestCase;
import lib.UI.OnBoardingPageObject;
import lib.UI.SearchPageObject;
import org.junit.jupiter.api.Test;

public class OnBoardingTests extends CoreTestCase {
    @Test
    public void testOnboardingFlow() {
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.assertLearnMoreLink();
        OnBoardingPageObject.nextButtonClick();
        OnBoardingPageObject.assertTitleNewWays();
        OnBoardingPageObject.nextButtonClick();
        OnBoardingPageObject.assertEditLanguageLink();
        OnBoardingPageObject.nextButtonClick();
        OnBoardingPageObject.assertLearnMoreDataLink();
        OnBoardingPageObject.clickGetStartedButton();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.assertSearchContainerIsPresented();
    }
}
