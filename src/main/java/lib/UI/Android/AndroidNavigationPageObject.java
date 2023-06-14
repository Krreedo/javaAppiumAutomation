package lib.UI.Android;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationPageObject;

public class AndroidNavigationPageObject extends NavigationPageObject {
    public AndroidNavigationPageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        SAVED_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";
    }
}
