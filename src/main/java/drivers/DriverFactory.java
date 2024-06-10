package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver getNewInstance(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome-headless":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--no-proxy-server");
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                return new FirefoxDriver();
            case "firefox-headless":
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                firefoxBinary.addCommandLineOptions("--window-size=1280x720");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                return new EdgeDriver();
            default:
                ChromeOptions defaultOptions = new ChromeOptions();
                defaultOptions.addArguments("start-maximized");
                defaultOptions.addArguments("--incognito");
                defaultOptions.addArguments("--disable-web-security");
                defaultOptions.addArguments("--no-proxy-server");
                defaultOptions.addArguments("--remote-allow-origins=*");
                defaultOptions.addArguments("--disable-notifications");
                return new ChromeDriver(defaultOptions);
        }
    }
}


//package drivers;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxBinary;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DriverFactory {
//
//    public static WebDriver getNewInstance(String browserName) {
//        switch (browserName.toLowerCase()) {
//            case "chrome-headless":
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless");
//                chromeOptions.addArguments("start-maximized");
//                chromeOptions.addArguments("--disable-web-security");
//                chromeOptions.addArguments("--no-proxy-server");
//                chromeOptions.addArguments("--remote-allow-origins=*");
//                return new ChromeDriver(chromeOptions);
//            case "firefox":
//                return new FirefoxDriver();
//            case "firefox-headless":
//                FirefoxBinary firefoxBinary = new FirefoxBinary();
//                firefoxBinary.addCommandLineOptions("--headless");
//                firefoxBinary.addCommandLineOptions("--window-size=1280x720");
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.setBinary(firefoxBinary);
//                return new FirefoxDriver(firefoxOptions);
//            case "edge":
//                return new EdgeDriver();
//            default:
//                chromeOptions = new ChromeOptions();
//                // TODO: handle browsers options
//                Map<String, Object> prefs = new HashMap<String, Object>();
//                  prefs.put("credentials_enable_service", false);
//                prefs.put("profile.password_manager_enabled", false);
//                prefs.put("profile.default_content_setting_values.notifications", 2);
//
//                chromeOptions.addArguments("start-maximized");
//                chromeOptions.addArguments("--incognito");
//                chromeOptions.addArguments("--disable-web-security");
//                chromeOptions.addArguments("--no-proxy-server");
//                chromeOptions.addArguments("--remote-allow-origins=*");
//                chromeOptions.addArguments("--disable-notifications");
//                chromeOptions.setExperimentalOption("prefs", prefs);
//                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//
//                DesiredCapabilities capabilities = new DesiredCapabilities();
//                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//                chromeOptions.merge(capabilities);
//
//                return new ChromeDriver(chromeOptions);
//        }
//    }
//
//
//}
