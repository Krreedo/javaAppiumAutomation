package lib.UI.Factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.Android.AndroidNavigationPageObject;
import lib.UI.Android.AndroidOnBoardingPageObject;
import lib.UI.IOS.IOSNavigationPageObject;
import lib.UI.IOS.IOSOnBoardingPageObject;
import lib.UI.NavigationPageObject;

public class NavigationPageObjectFactory {
    public static NavigationPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationPageObject(driver);
        } else {
            return new IOSNavigationPageObject(driver);
        }
    }
}
