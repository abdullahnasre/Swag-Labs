package testcases;

import drivers.DriverFactory;
import drivers.DriverHolder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestBase {
    protected static WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setupDriver(@Optional("chrome") String browser) {

        driver = DriverFactory.getNewInstance(browser);
        DriverHolder.setDriver(driver);
        driver.get("https://www.saucedemo.com/v1/");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}




//package testcases;
//
//import drivers.DriverFactory;
//import drivers.DriverHolder;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.*;
//
//public class TestBase {
//    WebDriver driver;
//
//    @BeforeTest
//    public void setupDriver() {
//        driver = DriverFactory.getNewInstance("");
//        DriverHolder.setDriver(driver);
//
//        driver.get("https://www.saucedemo.com/v1/");
//    }
//
//    @AfterTest
//    public void tearDown() {
//        if (driver != null){
//        driver.quit();
//        }
//        Thread.currentThread().interrupt();
//    }
//}
