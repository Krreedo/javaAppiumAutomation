package lib;

import io.appium.java_client.AppiumDriver;
import lib.UI.MainPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class CoreTestCase {
    protected AppiumDriver driver;
    protected MainPageObject MainPageObject;

    @BeforeEach
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        MainPageObject = new MainPageObject(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
