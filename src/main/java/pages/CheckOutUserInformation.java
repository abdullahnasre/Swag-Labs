package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutUserInformation {
    WebDriver driver;
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.xpath("//input[@value='CONTINUE']");

    public CheckOutUserInformation(WebDriver driver) {
        this.driver = driver;
    }

    public CheckOutUserInformation setFirstName(String firstNameField) {
        driver.findElement(firstName).sendKeys(firstNameField);
        return this;
    }

    public CheckOutUserInformation setLastName(String lastNameField) {
        driver.findElement(lastName).sendKeys(lastNameField);
        return this;
    }

    public CheckOutUserInformation setPostalCode(String postalCodeField) {
        driver.findElement(postalCode).sendKeys(postalCodeField);
        return this;
    }

    public CheckOutOverview clickContinueButton() {
        driver.findElement(continueButton).click();
        return new CheckOutOverview(driver);
    }
}
