package lib.UI;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class NavigationPageObject extends MainPageObject{
    public NavigationPageObject(AndroidDriver driver) {
        super(driver);
    }
    private static final String
    SAVED_BUTTON_ID = "org.wikipedia:id/nav_tab_reading_lists";
    public void savedPage(){
        waitForElementAndClick(
                By.id(SAVED_BUTTON_ID),
                "Not find Saved Button in tab-bar",
                5
        );
    }
}
