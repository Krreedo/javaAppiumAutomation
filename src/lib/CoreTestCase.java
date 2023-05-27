package lib;

import io.appium.java_client.AppiumDriver;
import lib.UI.MainPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {
    protected AppiumDriver driver;
    protected MainPageObject MainPageObject;
    private static final String
    IOS_PLATFORM_ENV = "ios",
    ANDROID_PLATFORM_ENV = "android";
@BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = getCapabilitiesByPlatformEnv();
        URL url = new URL("http://localhost:9090/");

        driver = new AppiumDriver(url, capabilities);
    MainPageObject = new MainPageObject(driver);
    }
@AfterEach
    public void tearDown() {
        driver.quit();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception{
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(ANDROID_PLATFORM_ENV)) {

            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "11.0");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/oleg.sofronov/Documents/JavaAppiumAutomation/javaAppiumAutomation/apks/Wikipedia_2.7.50437-r-2023-04-12_Apkpure.apk");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("deviceOrientation", "Portrait");
        } else if (platform.equals(IOS_PLATFORM_ENV)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "16.2");
            capabilities.setCapability("deviceName", "iPhone 14 Pro");
            capabilities.setCapability("app", "/Users/oleg.sofronov/Documents/JavaAppiumAutomation/javaAppiumAutomation/apks/Wikipedia.app");
            capabilities.setCapability("automationName", "XCUITest");


        } else {
            throw new Exception("Wrong environment : " + platform);
        }
        return capabilities;
    }
}

