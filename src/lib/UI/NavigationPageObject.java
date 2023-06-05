package lib.UI;

import io.appium.java_client.AppiumDriver;

public class NavigationPageObject extends MainPageObject {
    public NavigationPageObject(AppiumDriver driver) {
        super(driver);
    }

    public static String
            SAVED_BUTTON;

    public void savedPage() {
        waitForElementAndClick(
                SAVED_BUTTON,
                "Not find Saved Button in tab-bar",
                5
        );
    }
}
