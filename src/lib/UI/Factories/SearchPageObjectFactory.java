package lib.UI.Factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.Android.AndroidSearchPageObject;
import lib.UI.IOS.IOSSearchPageObject;
import lib.UI.SearchPageObject;

public class SearchPageObjectFactory {
    public static SearchPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        } else {
            return new IOSSearchPageObject(driver);
        }
    }
}
