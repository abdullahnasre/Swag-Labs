package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AddToCart {
    WebDriver driver;

    private final By checkOutButton = By.cssSelector(".btn_action.checkout_button");

    public AddToCart(WebDriver driver) {
        this.driver = driver;
    }

    public CheckOutUserInformation clickCheckOutButton() throws InterruptedException {
        Thread.sleep(3000);
        WebElement checkoutButton = driver.findElement(checkOutButton);
        checkoutButton.click();

        // Scroll to the checkout button
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

        Thread.sleep(2000);
        return new CheckOutUserInformation(driver);
    }

}
