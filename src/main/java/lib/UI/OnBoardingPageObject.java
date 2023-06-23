package lib.UI;

import org.openqa.selenium.remote.RemoteWebDriver;

public class OnBoardingPageObject extends MainPageObject {

    public OnBoardingPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static String
            ONBOARDING_SKIP_BUTTON,
            LEARN_MORE_LINK,
            ONBOARDING_NEXT_BUTTON,
            TITLE_LABEL_NEW_WAYS,
            EDIT_LANGUAGE_LINK,
            LEARN_MORE_DATA_LINK,
            GET_STARTED_BUTTON;

    public void skipOnboarding() {
        waitForElementAndClick(ONBOARDING_SKIP_BUTTON, "Not find Skip button", 15);
    }

    public void nextButtonClick() {
        waitForElementAndClick(ONBOARDING_NEXT_BUTTON, "Not find Next button", 15);
    }

    public void assertTitleNewWays() {
        waitForElementPresent(TITLE_LABEL_NEW_WAYS, "Not find Title on New ways to explore page", 15);
    }

    public void assertLearnMoreLink() {
        waitForElementPresent(LEARN_MORE_LINK, "Learn more link is not presented on this page", 15);
    }

    public void assertEditLanguageLink() {
        waitForElementPresent(EDIT_LANGUAGE_LINK, "Edit language link is not presented on this page", 15);
    }

    public void assertLearnMoreDataLink() {
        waitForElementPresent(LEARN_MORE_DATA_LINK, "Learn more data link is not presented on this page", 15);
    }

    public void clickGetStartedButton() {
        waitForElementAndClick(GET_STARTED_BUTTON, "Not find Get Started button", 15);
    }

}
