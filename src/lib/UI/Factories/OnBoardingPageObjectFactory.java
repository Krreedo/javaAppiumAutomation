package lib.UI.Factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.Android.AndroidOnBoardingPageObject;
import lib.UI.IOS.IOSOnBoardingPageObject;
import lib.UI.OnBoardingPageObject;

public class OnBoardingPageObjectFactory {
    public static OnBoardingPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidOnBoardingPageObject(driver);
        } else {
            return new IOSOnBoardingPageObject(driver);
        }
    }
}
