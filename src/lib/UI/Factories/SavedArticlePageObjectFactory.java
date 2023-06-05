package lib.UI.Factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.Android.AndroidOnBoardingPageObject;
import lib.UI.Android.AndroidSavedArticlesPageObject;
import lib.UI.IOS.IOSOnBoardingPageObject;
import lib.UI.IOS.IOSSavedArticlesPageObject;
import lib.UI.SavedArticlesPageObject;

public class SavedArticlePageObjectFactory {
    public static SavedArticlesPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedArticlesPageObject(driver);
        } else {
            return new IOSSavedArticlesPageObject(driver);
        }
    }
}
