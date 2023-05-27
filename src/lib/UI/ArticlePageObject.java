package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.regex.Pattern;

public class ArticlePageObject extends MainPageObject {
    private final static String
            SAVE_BUTTON = "id:org.wikipedia:id/page_save",
            ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action",
            ADD_TO_LIST_INPUT_FIELD = "id:org.wikipedia:id/text_input",
            LIST_TITLE= "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']",
            SNACKBAR_AFTER_SAVE = "id:org.wikipedia:id/fragment_page_coordinator",
            ARTICLE_TITLE = "xpath://android.view.View/android.view.View[1]/android.view.View[1]",
            ADD_TO_LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1'][@text='OK']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void firstSaveArticleToList(String list_name) {
        waitForElementAndClick(
                SAVE_BUTTON,
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Not find Add to List Button",
                5
        );
        waitForElementAndSendKeys(
                ADD_TO_LIST_INPUT_FIELD,
                "Not find text field",
                list_name,
                5
        );
        waitForElementAndClick(
                ADD_TO_LIST_OK_BUTTON,
                "Not find OK Button",
                15
        );


    }

    public void saveArticleToList(String list_name) {
        waitForElementAndClick(
                SAVE_BUTTON,
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Not find Add to List Button",
                5
        );
        waitForElementAndClick(
                getListTitleXpath(list_name),
                "Not find " + list_name + " list",
                5
        );
        waitForElementsPresent(
                SNACKBAR_AFTER_SAVE,
                "Snackbar not presented after article save",
                5
        );

    }

    private static String getListTitleXpath(String substring) {
        return LIST_TITLE.replace("{SUBSTRING}", substring);

    }

    public void assertArticleHasTitle(String article_title) {
        String article_title_actual = waitForElementPresent(
                ARTICLE_TITLE,
                "Not find title of article",
                5
        ).getText();
        Assertions.assertEquals(
                article_title,
                article_title_actual,
                "Wrong article title"
        );
    }



}
