package lib.UI.IOS;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationPageObject;

public class IOSNavigationPageObject extends NavigationPageObject {
    public IOSNavigationPageObject(AppiumDriver driver) {
        super(driver);
    }
    static {
        SAVED_BUTTON = "id:Saved";
    }
}
