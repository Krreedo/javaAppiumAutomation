package lib;

import io.appium.java_client.android.AndroidDriver;
import lib.UI.MainPageObject;
import lib.UI.OnBoardingPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {
    protected AndroidDriver driver;
    protected MainPageObject MainPageObject;
@BeforeEach
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/oleg.sofronov/Documents/JavaAppiumAutomation/javaAppiumAutomation/apks/Wikipedia_2.7.50437-r-2023-04-12_Apkpure.apk");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceOrientation", "Portrait");
        URL url = new URL("http://localhost:4723/");

        driver = new AndroidDriver(url, capabilities);
    MainPageObject = new MainPageObject(driver);
    }
@AfterEach
    public void tearDown() {
        driver.quit();
    }
}
