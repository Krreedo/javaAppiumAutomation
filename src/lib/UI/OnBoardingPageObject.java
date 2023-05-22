package lib.UI;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class OnBoardingPageObject extends MainPageObject{

    public OnBoardingPageObject(AndroidDriver driver) {
        super(driver);
    }
    private static final String
            ONBOARDING_SKIP_BUTTON_ID = "org.wikipedia:id/fragment_onboarding_skip_button",
            ONBOARDING_SKIP_BUTTON_XPATH = "//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button'][@text='SKIP']";
public void skipOnboarding(){
        waitForElementAndClick(By.xpath(ONBOARDING_SKIP_BUTTON_XPATH),"Not find Skip button",60);
}
}
