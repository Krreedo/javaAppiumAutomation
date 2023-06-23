package lib.UI;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationPageObject extends MainPageObject {
    public NavigationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static String
            SAVED_BUTTON,
            BURGER_MENU;

    public void savedPage() {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            waitForElementAndClick(
                    SAVED_BUTTON,
                    "Not find Saved Button in tab-bar",
                    5
            );
        } else {
            waitForElementAndClick(BURGER_MENU, "Burger menu not presented on this page", 5);
            tryClickElementWithAttempts(SAVED_BUTTON, "Cannot click Watchlist btn", 5);
        }

    }


}
