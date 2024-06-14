package testcases;

import drivers.DriverFactory;
import drivers.DriverHolder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setupDriver(@Optional("chrome") String browser) {

        driver = DriverFactory.getNewInstance(browser);
        DriverHolder.setDriver(driver);

        // set implicit wait
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/v1/");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
