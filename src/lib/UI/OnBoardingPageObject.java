package lib.UI;

import io.appium.java_client.AppiumDriver;

public class OnBoardingPageObject extends MainPageObject{

    public OnBoardingPageObject(AppiumDriver driver) {
        super(driver);
    }
    private static final String
            ONBOARDING_SKIP_BUTTON_ANDROID = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            ONBOARDING_SKIP_BUTTON_IOS = "xpath://XCUIElementTypeButton[@name='Skip']",
            LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
            ONBOARDING_NEXT_BUTTON_IOS = "xpath://XCUIElementTypeButton[@name='Next']",
            TITLE_LABEL_NEW_WAYS = "xpath://XCUIElementTypeStaticText[@name='New ways to explore']",
            EDIT_LANGUAGE_LINK = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
            LEARN_MORE_DATA_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
            GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']";

public void skipOnboarding(){
        waitForElementAndClick(ONBOARDING_SKIP_BUTTON_ANDROID,"Not find Skip button",15);
}
public void skipOnboardingIOS(){
        waitForElementAndClick(ONBOARDING_SKIP_BUTTON_IOS,"Not find Skip button",15);
}
public void nextButtonClick(){
    waitForElementAndClick(ONBOARDING_NEXT_BUTTON_IOS,"Not find Next button", 15);
}
public void assertTitleNewWays(){
    waitForElementPresent(TITLE_LABEL_NEW_WAYS, "Not find Title on New ways to explore page", 15);
}
public void assertLearnMoreLink(){
    waitForElementPresent(LEARN_MORE_LINK, "Learn more link is not presented on this page", 15);
}
public void assertEditLanguageLink(){
    waitForElementPresent(EDIT_LANGUAGE_LINK, "Edit language link is not presented on this page", 15);
}
public void assertLearnMoreDataLink(){
    waitForElementPresent(LEARN_MORE_DATA_LINK, "Learn more data link is not presented on this page", 15);
}
public void clickGetStartedButton(){
    waitForElementAndClick(GET_STARTED_BUTTON,"Not find Get Started button", 15);
}

}
