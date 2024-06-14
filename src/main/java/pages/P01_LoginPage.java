package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.findElement(this.USER_TEXT).sendKeys(email);
        return this;
    }

    public P01_LoginPage inputPassword(String password) {
        driver.findElement(this.PASSWORD_TEXT).sendKeys(password);
        return this;
    }

    public P01_LoginPage clickLoginButton() {
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
