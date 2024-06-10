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

























//package testcases;
//
//import org.testng.annotations.Test;
//import pages.CheckOutOverview;
//import pages.P01_LoginPage;
//import pages.P02_SelectProducts;
//import pages.AddToCart;
//
//public class TC02_CheckOutTest extends TestBase {
//
//    private final String firstName = "John";
//    private final String lastName = "Doe";
//    private final String postalCode = "12345";
//    private final String username = "standard_user";
//    private final String password = "secret_sauce";
//
//    @Test
//    public void checkOutTest() throws InterruptedException {
//        // Login
//        new P01_LoginPage(driver)
//                .inputEmail(username)
//                .inputPassword(password)
//                .clickLoginButton();
//
//        P02_SelectProducts selectProducts = new P02_SelectProducts(driver);
//
//        // Calculate the total price of the added items
//        double expectedTotalPrice = selectProducts.addItemsToCartAndCalculateTotalPrice();
//        System.out.println("Expected total price (without tax): $" + expectedTotalPrice);
//
//        // Navigate to the cart page and get the total price displayed
//        selectProducts.clickOnShoppingCartButton();
//
//        new AddToCart(driver)
//                .clickCheckOutButton()
//                .setFirstName(firstName)
//                .setLastName(lastName)
//                .setPostalCode(postalCode)
//                .clickContinueButton();
//
//        CheckOutOverview checkOut = new CheckOutOverview(driver);
//
//        // Fetch the tax amount
//        String taxStr = checkOut.getTax().replace("Tax: $", "");
//        double taxAmount = Double.parseDouble(taxStr);
//        System.out.println("Tax amount: $" + taxAmount);
//
//        // Add the tax amount to the expected total price
//        expectedTotalPrice += taxAmount;
//        System.out.println("Expected total price (with tax): $" + expectedTotalPrice);
//
//        // Fetch the actual total price from the checkout overview
//        String totalStr = checkOut.getTotal().replace("Total: $", "");
//        double actualTotalPrice = Double.parseDouble(totalStr);
//        System.out.println("Actual total price: $" + actualTotalPrice);
//
//        // Round both values to two decimal places before comparison
//        expectedTotalPrice = Math.round(expectedTotalPrice * 100.0) / 100.0;
//        actualTotalPrice = Math.round(actualTotalPrice * 100.0) / 100.0;
//
//        // Assert that the expected total price matches the actual total price
//        assert expectedTotalPrice == actualTotalPrice : "Total price does not match! Expected: $" + expectedTotalPrice + ", but found: $" + actualTotalPrice;
//    }
//}


//package testcases;
//
//import org.testng.annotations.Test;
//import pages.CheckOutOverview;
//import pages.P01_LoginPage;
//import pages.P02_SelectProducts;
//import pages.AddToCart;
//import util.Utility;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class TC02_CheckOutTest extends TestBase {
//
//    private final String firstName = "John";
//    private final String lastName = "Doe";
//    private final String postalCode = "12345";
//    private final String username = "standard_user";
//    private final String password = "secret_sauce";
//
//    @Test
//    public void checkOutTest() throws InterruptedException {
//        // Login
//        new P01_LoginPage(driver)
//                .inputEmail(username)
//                .inputPassword(password)
//                .clickLoginButton();
//
//        P02_SelectProducts selectProducts = new P02_SelectProducts(driver);
//
//        // Get the shuffled list of item indices
//        List<Integer> shuffledIndices = Utility.getShuffledItemIndices();
//
//        // List to store the prices of the items added to the cart
//        List<Double> prices = new ArrayList<>();
//
//        // Iterate through the shuffled indices, add items to the cart, and sum the prices
//        for (int index : shuffledIndices) {
//            String priceStr = null;
//            switch (index) {
//                case 1:
//                    selectProducts.clickOnBackpackItem();
//                    priceStr = selectProducts.getBackPackPrice();
//                    break;
//                case 2:
//                    selectProducts.clickOnBikeLightItem();
//                    priceStr = selectProducts.getBikeLightPrice();
//                    break;
//                case 3:
//                    selectProducts.clickOnBoltTshirtItem();
//                    priceStr = selectProducts.getBoltTshirtPrice();
//                    break;
//                case 4:
//                    selectProducts.clickOnFleeceJacketItem();
//                    priceStr = selectProducts.getFleeceJacketPrice();
//                    break;
//                case 5:
//                    selectProducts.clickOnOnesieItem();
//                    priceStr = selectProducts.getOnesiePrice();
//                    break;
//                case 6:
//                    selectProducts.clickOnTshirtRedItem();
//                    priceStr = selectProducts.getTshirtRedPrice();
//                    break;
//            }
//
//            // Check if priceStr is not null before parsing
//            if (priceStr != null) {
//                // Remove the "$" sign before parsing
//                priceStr = priceStr.substring(1);
//                prices.add(Double.parseDouble(priceStr));
//            }
//        }
//
//        // Calculate the total price of the added items
//        double expectedTotalPrice = prices.stream().mapToDouble(Double::doubleValue).sum();
//        System.out.println("Expected total price (without tax): $" + expectedTotalPrice);
//
//        // Navigate to the cart page and get the total price displayed
//        new P02_SelectProducts(driver).clickOnShoppingCartButton();
//
//        new AddToCart(driver)
//                .clickCheckOutButton()
//                .setFirstName(firstName)
//                .setLastName(lastName)
//                .setPostalCode(postalCode)
//                .clickContinueButton();
//
//        CheckOutOverview checkOut = new CheckOutOverview(driver);
//
//        // Fetch the tax amount
//        String taxStr = checkOut.getTax().replace("Tax: $", "");
//        double taxAmount = Double.parseDouble(taxStr);
//        System.out.println("Tax amount: $" + taxAmount);
//
//        // Add the tax amount to the expected total price
//        expectedTotalPrice += taxAmount;
//        System.out.println("Expected total price (with tax): $" + expectedTotalPrice);
//
//        // Fetch the actual total price from the checkout overview
//        String totalStr = checkOut.getTotal().replace("Total: $", "");
//        double actualTotalPrice = Double.parseDouble(totalStr);
//        System.out.println("Actual total price: $" + actualTotalPrice);
//
//        // Round both values to two decimal places before comparison
//        expectedTotalPrice = Math.round(expectedTotalPrice * 100.0) / 100.0;
//        actualTotalPrice = Math.round(actualTotalPrice * 100.0) / 100.0;
//
//
//        // Assert that the expected total price matches the actual total price
//        assert expectedTotalPrice == actualTotalPrice : "Total price does not match! Expected: $" + expectedTotalPrice + ", but found: $" + actualTotalPrice;
//    }
//}


//package testcases;
//
//import org.testng.annotations.Test;
//import pages.CheckOutOverview;
//import pages.P01_LoginPage;
//import pages.P02_SelectProducts;
//import pages.AddToCart;
//import util.Utility;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class TC02_CheckOutTest extends TestBase {
//
//    private final String firstName = "John";
//    private final String lastName = "Doe";
//    private final String postalCode = "12345";
//    private final String username = "standard_user";
//    private final String password = "secret_sauce";
//
//    @Test
//    public void checkOutTest() throws InterruptedException {
//        // Login
//        new P01_LoginPage(driver)
//                .inputEmail(username)
//                .inputPassword(password)
//                .clickLoginButton();
//
//        P02_SelectProducts selectProducts = new P02_SelectProducts(driver);
//
//        // Get the shuffled list of item indices
//        List<Integer> shuffledIndices = Utility.getShuffledItemIndices();
//
//        // List to store the prices of the items added to the cart
//        List<Double> prices = new ArrayList<>();
//
//        // Map to store the prices of the items
//        Map<Integer, Double> itemPrices = new HashMap<>();
//        // Populate item prices
//        itemPrices.put(1, 29.99); // Backpack
//        itemPrices.put(2, 9.99);  // Bike Light
//        itemPrices.put(3, 15.99); // Bolt T-shirt
//        itemPrices.put(4, 49.99); // Fleece Jacket
//        itemPrices.put(5, 7.99);  // Onesie
//        itemPrices.put(6, 15.99); // Red T-shirt
//
//        // Iterate through the shuffled indices, add items to the cart, and sum the prices
//        for (int index : shuffledIndices) {
//            String priceStr = null;
//            switch (index) {
//                case 1:
//                    selectProducts.clickOnBackpackItem();
//                    priceStr = selectProducts.getBackPackPrice();
//                    break;
//                case 2:
//                    selectProducts.clickOnBikeLightItem();
//                    priceStr = selectProducts.getBikeLightPrice();
//                    break;
//                case 3:
//                    selectProducts.clickOnBoltTshirtItem();
//                    priceStr = selectProducts.getBoltTshirtPrice();
//                    break;
//                case 4:
//                    selectProducts.clickOnFleeceJacketItem();
//                    priceStr = selectProducts.getFleeceJacketPrice();
//                    break;
//                case 5:
//                    selectProducts.clickOnOnesieItem();
//                    priceStr = selectProducts.getOnesiePrice();
//                    break;
//                case 6:
//                    selectProducts.clickOnTshirtRedItem();
//                    priceStr = selectProducts.getTshirtRedPrice();
//                    break;
//            }
//
//            // Check if priceStr is not null before parsing
//            if (priceStr != null) {
//                // Remove the "$" sign before parsing
//                priceStr = priceStr.substring(1);
//                prices.add(Double.parseDouble(priceStr));
//            }
//        }
//
//        // Calculate the total price of the added items
//        double expectedTotalPrice = prices.stream().mapToDouble(Double::doubleValue).sum();
//        System.out.println("Expected total price (without tax): $" + expectedTotalPrice);
//
//        // Navigate to the cart page and get the total price displayed
//        new P02_SelectProducts(driver).clickOnShoppingCartButton();
//
//        new AddToCart(driver)
//                .clickCheckOutButton()
//                .setFirstName(firstName)
//                .setLastName(lastName)
//                .setPostalCode(postalCode)
//                .clickContinueButton();
//
//        CheckOutOverview checkOut = new CheckOutOverview(driver);
//
//        // Fetch the tax amount
//        String taxStr = checkOut.getTax().replace("Tax: $", "");
//        double taxAmount = Double.parseDouble(taxStr);
//        System.out.println("Tax amount: $" + taxAmount);
//
//        // Add the tax amount to the expected total price
//        expectedTotalPrice += taxAmount;
//        System.out.println("Expected total price (with tax): $" + expectedTotalPrice);
//
//        // Fetch the actual total price from the checkout overview
//        String totalStr = checkOut.getTotal().replace("Total: $", "");
//        double actualTotalPrice = Double.parseDouble(totalStr);
//        System.out.println("Actual total price: $" + actualTotalPrice);
//
//        // Assert that the expected total price matches the actual total price
//        assert expectedTotalPrice == actualTotalPrice : "Total price does not match! Expected: $" + expectedTotalPrice + ", but found: $" + actualTotalPrice;
//    }
//}
//
//
//
////package testcases;
////
////import org.testng.annotations.Test;
////import pages.CheckOutOverview;
////import pages.P01_LoginPage;
////import pages.P02_SelectProducts;
////import pages.AddToCart;
////import util.Utility;
////
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.List;
////import java.util.Map;
////
////public class TC02_CheckOutTest extends TestBase {
////
////    private final String firstName = "John";
////    private final String lastName = "Doe";
////    private final String postalCode = "12345";
////    private final String username = "standard_user";
////    private final String password = "secret_sauce";
////
////    @Test
////    public void checkOutTest() throws InterruptedException {
////        // Login
////        new P01_LoginPage(driver)
////                .inputEmail(username)
////                .inputPassword(password)
////                .clickLoginButton();
////
////        P02_SelectProducts selectProducts = new P02_SelectProducts(driver);
////
////        // Get the shuffled list of item indices
////        List<Integer> shuffledIndices = Utility.getShuffledItemIndices();
////
////        // List to store the prices of the items added to the cart
////        List<Double> prices = new ArrayList<>();
////
////        // Map to store the prices of the items
////        Map<Integer, Double> itemPrices = new HashMap<>();
////        // Populate item prices
////        itemPrices.put(1, 29.99); // Backpack
////        itemPrices.put(2, 9.99);  // Bike Light
////        itemPrices.put(3, 15.99); // Bolt T-shirt
////        itemPrices.put(4, 49.99); // Fleece Jacket
////        itemPrices.put(5, 7.99);  // Onesie
////        itemPrices.put(6, 15.99); // Red T-shirt
////
////        // Iterate through the shuffled indices, add items to the cart, and sum the prices
////        for (int index : shuffledIndices) {
////            String priceStr = null;
////            switch (index) {
////                case 1:
////                    selectProducts.clickOnBackpackItem();
////                    priceStr = selectProducts.getBackPackPrice();
////                    break;
////                case 2:
////                    selectProducts.clickOnBikeLightItem();
////                    priceStr = selectProducts.getBikeLightPrice();
////                    break;
////                case 3:
////                    selectProducts.clickOnBoltTshirtItem();
////                    priceStr = selectProducts.getBoltTshirtPrice();
////                    break;
////                case 4:
////                    selectProducts.clickOnFleeceJacketItem();
////                    priceStr = selectProducts.getFleeceJacketPrice();
////                    break;
////                case 5:
////                    selectProducts.clickOnOnesieItem();
////                    priceStr = selectProducts.getOnesiePrice();
////                    break;
////                case 6:
////                    selectProducts.clickOnTshirtRedItem();
////                    priceStr = selectProducts.getTshirtRedPrice();
////                    break;
////            }
////
////            // Check if priceStr is not null before parsing
////            if (priceStr != null) {
////                // Remove the "$" sign before parsing
////                priceStr = priceStr.substring(1);
////                prices.add(Double.parseDouble(priceStr));
////            }
////        }
////
////        // Calculate the total price of the added items
////        double expectedTotalPrice = prices.stream().mapToDouble(Double::doubleValue).sum();
////        System.out.println("Expected total price: $" + expectedTotalPrice);
////
////        // Navigate to the cart page and get the total price displayed
////        new P02_SelectProducts(driver).clickOnShoppingCartButton();
////
////        new AddToCart(driver)
////                .clickCheckOutButton()
////                .setFirstName(firstName)
////                .setLastName(lastName)
////                .setPostalCode(postalCode)
////                .clickContinueButton();
////
////        CheckOutOverview checkOut = new CheckOutOverview(driver);
////        Thread.sleep(3000);
////        String totalStr = checkOut.getTotal().replace("Total: $", "");
////        double actualTotalPrice = Double.parseDouble(totalStr);
////        System.out.println("Actual total price: $" + actualTotalPrice);
////
////        // Assert that the expected total price matches the actual total price
////        assert expectedTotalPrice == actualTotalPrice : "Total price does not match! Expected: $" + expectedTotalPrice + ", but found: $" + actualTotalPrice;
////    }
////}
////
