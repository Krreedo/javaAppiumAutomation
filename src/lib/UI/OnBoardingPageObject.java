package lib.UI;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class OnBoardingPageObject extends MainPageObject{

    public OnBoardingPageObject(AndroidDriver driver) {
        super(driver);
    }
    private static final String
            ONBOARDING_SKIP_BUTTON_ID = "org.wikipedia:id/fragment_onboarding_skip_button";
public void skipOnboarding(){
        waitForElementAndClick(By.id(ONBOARDING_SKIP_BUTTON_ID),"Not find Skip button",150000000);
}
}
