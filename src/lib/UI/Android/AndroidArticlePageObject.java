package lib.UI.Android;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        ADD_TO_LIST_INPUT_FIELD = "id:org.wikipedia:id/text_input";
        LIST_TITLE = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']";
        SNACKBAR_AFTER_SAVE = "id:org.wikipedia:id/fragment_page_coordinator";
        ARTICLE_TITLE = "xpath://android.view.View/android.view.View[1]/android.view.View[1]";
        ADD_TO_LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1'][@text='OK']";
    }
}
