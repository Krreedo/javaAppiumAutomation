package lib;

import lib.UI.MainPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase {
    protected RemoteWebDriver driver;
    protected MainPageObject MainPageObject;

    @BeforeEach
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
//        MainPageObject = new MainPageObject(driver);
        openWikiPageForMobileWeb();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    protected void openWikiPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org/");
        } else {
            System.out.println("Method openWikiPageForMobileWeb do nothing for platform " + Platform.getInstance().getPlatformEnv());
        }
    }
}
