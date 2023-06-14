package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static Platform instance;

    private Platform() {
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    private static final String
            APPIUM_URL = "http://localhost:9090/",
            IOS_PLATFORM_ENV = "ios",
            ANDROID_PLATFORM_ENV = "android";

    public boolean isAndroid() {
        return isPlatform(ANDROID_PLATFORM_ENV);
    }

    public boolean isIOS() {
        return isPlatform(IOS_PLATFORM_ENV);
    }

    public AppiumDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (isAndroid()) {
            return new AndroidDriver(url, getAndroidDesiredCapabilities());
        } else if (isIOS()) {
            return new IOSDriver(url, getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of driver. Platform value: " + getPlatformEnv());
        }

    }


    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "D:/IdeaProjects/javaAppiumAutomation/apks/Wikipedia_2.7.50437-r-2023-04-12_Apkpure.apk");
        capabilities.setCapability("automationName", "UiAutomator2");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "16.2");
        capabilities.setCapability("deviceName", "iPhone 14 Pro");
        capabilities.setCapability("app", "/Users/oleg.sofronov/Documents/JavaAppiumAutomation/javaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("automationName", "XCUITest");
        return capabilities;
    }

    private String getPlatformEnv() {
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String my_platform) {
        String platform = getPlatformEnv();
        return my_platform.equals(platform);
    }
}
