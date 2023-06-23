package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
            ANDROID_PLATFORM_ENV = "android",
            MOBILE_WEB_ENV = "mw";


    public boolean isAndroid() {
        return isPlatform(ANDROID_PLATFORM_ENV);
    }

    public boolean isIOS() {
        return isPlatform(IOS_PLATFORM_ENV);
    }

    public boolean isMW() {
        return isPlatform(MOBILE_WEB_ENV);
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (isAndroid()) {
            return new AndroidDriver(url, getAndroidDesiredCapabilities());
        } else if (isIOS()) {
            return new IOSDriver(url, getIOSDesiredCapabilities());
        } else if (isMW()) {
            return new ChromeDriver(getMWChromeOptions());

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

    private ChromeOptions getMWChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        String userAgent = "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 320);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);
        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", userAgent);
        chromeOptions.addArguments("--window-size=320,980");
        chromeOptions.addArguments("--force-device-scale-factor=1.0");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        return chromeOptions;

    }

    protected String getPlatformEnv() {
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String my_platform) {
        String platform = getPlatformEnv();
        return my_platform.equals(platform);
    }
}
