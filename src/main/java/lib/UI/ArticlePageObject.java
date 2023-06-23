package lib.UI;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject {
    public static String
            SAVE_BUTTON,
            ADD_TO_LIST_BUTTON,
            ADD_TO_LIST_BUTTON_IS_PRESSED,
            ADD_TO_LIST_INPUT_FIELD,
            LIST_TITLE,
            SNACKBAR_AFTER_SAVE,
            ARTICLE_TITLE,
            CREATE_LIST_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            ADD_TO_LIST_BUTTON_FROM_SNACKBAR,
            TABLE_OF_CONTENTS_BUTTON,
            ARTICLE_TITLE_IN_CONTENTS,
            LOG_IN_BUTTON,
            CLOSE_POP_UP_MW,
            ADD_TO_LIST_OK_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
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

    public void saveFirstArticleToListIOS(String list_name, String article_title) {
        waitForElementAndClick(
                SAVE_BUTTON,
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                getAddToListXpath(article_title),
                "Not find Add to List Button",
                5
        );
        waitForElementAndClick(
                CREATE_NEW_LIST_BUTTON,
                "Not find Create a new list Button",
                5
        );
        waitForElementAndSendKeys(
                ADD_TO_LIST_INPUT_FIELD,
                "Not find Input field",
                list_name,
                5);
        waitForElementAndClick(
                CREATE_LIST_BUTTON,
                "Not find Create Button",
                5);
    }

    public void saveArticleToListIOS(String list_name, String article_title) {
        waitForElementAndClick(
                SAVE_BUTTON,
                "Not find Save Button",
                5
        );
        waitForElementAndClick(
                getAddToListXpath(article_title),
                "Not find Add to List Button",
                5
        );

        waitForElementAndClick(
                getListID(list_name),
                "Not find list title " + list_name,
                5
        );
        waitForElementsPresent(
                SNACKBAR_AFTER_SAVE,
                "Snackbar not presented after article save",
                5
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

    private static String getAddToListXpath(String substring) {
        return ADD_TO_LIST_BUTTON_FROM_SNACKBAR.replace("{SUBSTRING}", substring);

    }

    private static String getArticleTitleID(String title) {
        return ARTICLE_TITLE.replace("{TITLE}", title);
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

    public void assertArticleTitleInTableOfContents(String article_title) {
        waitForElementAndClick(TABLE_OF_CONTENTS_BUTTON, "Not find Table of contents button", 5);
        String article_title_actual = waitForElementPresent(ARTICLE_TITLE_IN_CONTENTS, "some error", 5).getText();
        Assertions.assertEquals(
                article_title,
                article_title_actual,
                "Wrong article title"
        );

    }

    public static String getListID(String list_name) {
        return "id:" + list_name;
    }

    public void firstSaveArticleMW() {
        if (isElementPresented(CLOSE_POP_UP_MW, 2)) {
            waitForElementAndClick(CLOSE_POP_UP_MW, "Cannot close pop-up", 5);
        }
        waitForElementAndClick(SAVE_BUTTON, "Cannot click Save btn", 5);
        waitForElementAndClick(LOG_IN_BUTTON, "Cannot click Login btn", 5);
    }

    public void saveArticleMW(String article_title) {
        if (isElementPresented(ADD_TO_LIST_BUTTON_IS_PRESSED, 2)) {
            assertArticleHasTitle(article_title);
            System.out.println("Article already added to watchlist");
        } else {
            if (isElementPresented(CLOSE_POP_UP_MW, 2)) {
                waitForElementAndClick(CLOSE_POP_UP_MW, "Cannot close pop-up", 5);
            }
            assertArticleHasTitle(article_title);
            waitForElementAndClick(SAVE_BUTTON, "Cannot click Save btn", 5);
            assertArticleHasAddedToWatchList();
        }
    }


    public void assertArticleHasAddedToWatchList() {
        waitForElementPresent(ADD_TO_LIST_BUTTON_IS_PRESSED, "Article not added to list", 5);
    }
}
