package lib.UI.Factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.Android.AndroidArticlePageObject;
import lib.UI.Android.AndroidOnBoardingPageObject;
import lib.UI.ArticlePageObject;
import lib.UI.IOS.IOSArticlePageObject;
import lib.UI.IOS.IOSOnBoardingPageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return new IOSArticlePageObject(driver);
        }
    }
}
