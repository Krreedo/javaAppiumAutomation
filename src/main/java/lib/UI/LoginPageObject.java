package lib.UI;

import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPageObject extends MainPageObject {
    public LoginPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static String
            LOGIN_FIELD,
            PASSWORD_FIELD,
            SUBMIT_BUTTON;

    public void enterCredentials(String username, String password) {
        // credentials username Krreedo password -6u3SsLnkD?cMS.
        waitForElementAndSendKeys(LOGIN_FIELD, "Login field is not presented", username, 5);
        waitForElementAndSendKeys(PASSWORD_FIELD, "Password field is not presented", password, 5);
    }

    public void submitLogin() {
        waitForElementAndClick(SUBMIT_BUTTON, "Submit button not presented", 5);
    }
}
