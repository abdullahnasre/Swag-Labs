package testcases;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P01_LoginPage;
import util.Utility;

import java.io.IOException;

public class TC01_Login extends TestBase {

    // define test data
    String user = Utility.getExcelData(0, 0, "Sheet1");
    String password = Utility.getExcelData(1, 0, "Sheet1");

    // check login positive scenarios
    @Test(priority = 1, description = "Login with Valid Username and Password")
    public void loginWithValidData_P() {
        new P01_LoginPage(driver).inputEmail(user).inputPassword(password).clickLoginButton();
        //hard assertion after successfully login
        Assert.assertTrue(new P01_LoginPage(driver).verifyLoginSuccessfully());
        Assert.assertFalse(!new P01_LoginPage(driver).verifyLoginSuccessfully());
        Assert.assertEquals(new P01_LoginPage(driver).verifyLogin(), "Products");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        Assert.assertTrue(new P01_LoginPage(driver).verfiyADDButtonVisible());

        // soft assertion
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(new P01_LoginPage(driver).verifyLogin(), "Products");
        soft.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        soft.assertTrue(new P01_LoginPage(driver).verfiyADDButtonVisible());
        soft.assertAll();

    }


    @Test(priority = 2, description = "Login with Invalid Username ")
    public void loginWithInvalidUserName_N() {
        new P01_LoginPage(driver).inputEmail(user).inputPassword(password).clickLoginButton();
    }

    @Test(priority = 3, description = "Login with Invalid Password")
    public void loginWithInvalidPassword_N() {
        new P01_LoginPage(driver).inputEmail(user).inputPassword(password).clickLoginButton();
    }

    @Test(priority = 4, description = "Login with Invalid email and Password")
    public void loginWithValidData_N() {
        new P01_LoginPage(driver).inputEmail(user).inputPassword(password).clickLoginButton();
    }
}
