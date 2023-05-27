package lib.UI;

import io.appium.java_client.AppiumDriver;

public class NavigationPageObject extends MainPageObject{
    public NavigationPageObject(AppiumDriver driver) {
        super(driver);
    }
    private static final String
    SAVED_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";
    public void savedPage(){
        waitForElementAndClick(
                SAVED_BUTTON,
                "Not find Saved Button in tab-bar",
                5
        );
    }
}
