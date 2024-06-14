package pages;

import org.openqa.selenium.By;

import static org.junit.Assert.fail;
import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P01_LoginPage {

    WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By USER_TEXT = By.xpath("//input[@id='user-name']");
    private final By PASSWORD_TEXT = By.xpath("//input[@id='password']");
    private final By LOGIN_BUTTON = By.xpath("//input[@id='login-button']");
    private final By PRODUCT_TITLE = By.xpath("(//div[@class=\"product_label\"])");
    private final By ADD_TO_CARD_BUTTON = By.xpath("(//button[@class=\"btn_primary btn_inventory\"])[1]");

    public P01_LoginPage inputEmail(String email) {
        try {
            longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(USER_TEXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }

        driver.findElement(this.USER_TEXT).sendKeys(email);
        return this;
    }

    public P01_LoginPage inputPassword(String password) {
        // TODO: explicit wait
        PageBase.explicitWait(driver, PASSWORD_TEXT);

        //TODO: perform action
        driver.findElement(this.PASSWORD_TEXT).sendKeys(password);
        return this;
    }

    public P01_LoginPage clickLoginButton() {
//        PageBase.explicitWait(driver, PRODUCT_TITLE);
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }

        driver.findElement(this.LOGIN_BUTTON).click();
        return this;
    }

    public boolean verifyLoginSuccessfully() {
        return driver.findElement(PRODUCT_TITLE).getText().contains("Products");
    }

    public String verifyLogin() {
        return driver.findElement(PRODUCT_TITLE).getText();
    }

    public boolean verfiyADDButtonVisible() {

        return driver.findElement(ADD_TO_CARD_BUTTON).isDisplayed();
    }

}
