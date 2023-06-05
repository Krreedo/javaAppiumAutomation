package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.Factories.SearchPageObjectFactory;
import lib.UI.OnBoardingPageObject;
import lib.UI.SearchPageObject;
import org.junit.jupiter.api.Test;

public class OnBoardingTests extends CoreTestCase {
    @Test
    public void testOnboardingFlow() {
        if(Platform.getInstance().isAndroid()){
            return;
        }
        OnBoardingPageObject OnBoardingPageObject = new OnBoardingPageObject(driver);
        OnBoardingPageObject.assertLearnMoreLink();
        OnBoardingPageObject.nextButtonClick();
        OnBoardingPageObject.assertTitleNewWays();
        OnBoardingPageObject.nextButtonClick();
        OnBoardingPageObject.assertEditLanguageLink();
        OnBoardingPageObject.nextButtonClick();
        OnBoardingPageObject.assertLearnMoreDataLink();
        OnBoardingPageObject.clickGetStartedButton();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.assertSearchContainerIsPresented();
    }
}
