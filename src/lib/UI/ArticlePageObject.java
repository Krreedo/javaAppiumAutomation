package lib.UI;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {
    private final static String
            SAVE_BUTTON_ID = "org.wikipedia:id/page_save",
            ADD_TO_LIST_BUTTON_ID = "org.wikipedia:id/snackbar_action",
            ADD_TO_LIST_INPUT_FIELD_ID = "org.wikipedia:id/text_input",
            LIST_TITLE_XPATH = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']",
            SNACKBAR_AFTER_SAVE_ID = "org.wikipedia:id/fragment_page_coordinator",
            ARTICLE_TITLE_XPATH = "//android.view.View/android.view.View[1]/android.view.View[1]",
            ADD_TO_LIST_OK_BUTTON_XPATH = "//*[@resource-id='android:id/button1'][@text='OK']";

    public ArticlePageObject(AndroidDriver driver) {
        super(driver);
    }

    public void firstSaveArticleToList(String list_name) {
        waitForElementAndClick(
                By.id(SAVE_BUTTON_ID),
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON_ID),
                "Not find Add to List Button",
                5
        );
        waitForElementAndSendKeys(
                By.id(ADD_TO_LIST_INPUT_FIELD_ID),
                "Not find text field",
                list_name,
                5
        );
        waitForElementAndClick(
                By.xpath(ADD_TO_LIST_OK_BUTTON_XPATH),
                "Not find OK Button",
                15
        );


    }

    public void saveArticleToList(String list_name) {
        waitForElementAndClick(
                By.id(SAVE_BUTTON_ID),
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON_ID),
                "Not find Add to List Button",
                5
        );
        waitForElementAndClick(
                By.xpath(getListTitleXpath(list_name)),
                "Not find " + list_name + " list",
                5
        );
        waitForElementsPresent(
                By.id(SNACKBAR_AFTER_SAVE_ID),
                "Snackbar not presented after article save",
                5
        );

    }

    private static String getListTitleXpath(String substring) {
        return LIST_TITLE_XPATH.replace("{SUBSTRING}", substring);

    }

    public void assertArticleHasTitle(String article_title) {
        String article_title_actual = waitForElementPresent(
                By.xpath(ARTICLE_TITLE_XPATH),
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
