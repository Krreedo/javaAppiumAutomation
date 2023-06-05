package lib.UI.IOS;

import io.appium.java_client.AppiumDriver;
import lib.UI.SavedArticlesPageObject;

public class IOSSavedArticlesPageObject extends SavedArticlesPageObject {
    public IOSSavedArticlesPageObject(AppiumDriver driver) {
        super(driver);
    }
    static {
        READING_LISTS_IOS = "xpath://XCUIElementTypeStaticText[@name='Reading lists']";
        SAVED_LIST_NAME = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
                ARTICLE_ELEMENT = "xpath://*[@type='XCUIElementTypeCell']";
                SYNC_POP_UP_CLOSE_BUTTON = "id:Close";
                DELETE_BUTTON = "id:swipe action delete";
                ARTICLE_NAME = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
    }
}
