package testcases;

import org.testng.annotations.Test;
import pages.CheckOutOverview;
import pages.P01_LoginPage;
import pages.P02_SelectProducts;
import pages.AddToCart;

public class TC02_CheckOutTest extends TestBase {

    private final String firstName = "John";
    private final String lastName = "Doe";
    private final String postalCode = "12345";
    private final String username = "standard_user";
    private final String password = "secret_sauce";

    @Test
    public void checkOutTest() throws InterruptedException {
        // Login
        new P01_LoginPage(driver)
                .inputEmail(username)
                .inputPassword(password)
                .clickLoginButton();

        P02_SelectProducts selectProducts = new P02_SelectProducts(driver);

        // Calculate the total price of the added items
        double expectedTotalPrice = selectProducts.addItemsToCartAndCalculateTotalPrice();
        System.out.println("Expected total price (without tax): $" + expectedTotalPrice);

        // Navigate to the cart page and get the total price displayed
        selectProducts.clickOnShoppingCartButton();

        new AddToCart(driver)
                .clickCheckOutButton()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPostalCode(postalCode)
                .clickContinueButton();

        CheckOutOverview checkOut = new CheckOutOverview(driver);

        // Fetch the tax amount
        String taxStr = checkOut.getTax().replace("Tax: $", "");
        double taxAmount = Double.parseDouble(taxStr);
        System.out.println("Tax amount: $" + taxAmount);

        // Add the tax amount to the expected total price
        expectedTotalPrice += taxAmount;
        System.out.println("Expected total price (with tax): $" + expectedTotalPrice);

        // Fetch the actual total price from the checkout overview
        double actualTotalPrice = checkOut.getActualTotalPrice();

        // Round both values to two decimal places before comparison
        expectedTotalPrice = CheckOutOverview.roundToTwoDecimalPlaces(expectedTotalPrice);
        actualTotalPrice = CheckOutOverview.roundToTwoDecimalPlaces(actualTotalPrice);

        // Assert that the expected total price matches the actual total price
        assert expectedTotalPrice == actualTotalPrice : "Total price does not match! Expected: $" + expectedTotalPrice + ", but found: $" + actualTotalPrice;
    }
}

