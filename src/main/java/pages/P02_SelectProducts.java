package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

public class P02_SelectProducts {

    WebDriver driver;

    private final By backpackItem = By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]");
    private final By bikeLightItem = By.xpath("//body//div[@id='page_wrapper']//div[@id='inventory_container']//div[@id='inventory_container']//div[2]//div[3]//button[1]");
    private final By boltTshirtItem = By.xpath("//div[3]//div[3]//button[1]");
    private final By fleeceJacketItem = By.xpath("//div[4]//div[3]//button[1]");
    private final By onesieItem = By.xpath("//div[5]//div[3]//button[1]");
    private final By tshirtRedItem = By.xpath("//div[6]//div[3]//button[1]");
    private final By backPackPrice = By.xpath("//div[@class='inventory_item_price'][text()='29.99']");
    private final By bikeLightPrice = By.xpath("//div[@class='inventory_item_price'][text()='9.99']");
    private final By boltTshirtPrice = By.xpath("//div[@class='inventory_item_price'][text()='15.99']");
    private final By fleeceJacketPrice = By.xpath("//div[@class='inventory_item_price'][text()='49.99']");
    private final By onesiePrice = By.xpath("//div[@class='inventory_item_price'][text()='7.99']");
    private final By tshirtRedPrice = By.xpath("//div[@class='inventory_item_price'][text()='15.99']");
    private final By shoppingCartButton = By.id("shopping_cart_container");

    public P02_SelectProducts(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnBackpackItem() {
        driver.findElement(backpackItem).click();
    }

    public void clickOnBikeLightItem() {
        driver.findElement(bikeLightItem).click();
    }

    public void clickOnBoltTshirtItem() {
        driver.findElement(boltTshirtItem).click();
    }

    public void clickOnFleeceJacketItem() {
        driver.findElement(fleeceJacketItem).click();
    }

    public void clickOnOnesieItem() {
        driver.findElement(onesieItem).click();
    }

    public void clickOnTshirtRedItem() {
        driver.findElement(tshirtRedItem).click();
    }

    public String getBackPackPrice() {
        return driver.findElement(backPackPrice).getText();
    }

    public String getBikeLightPrice() {
        return driver.findElement(bikeLightPrice).getText();
    }

    public String getBoltTshirtPrice() {
        return driver.findElement(boltTshirtPrice).getText();
    }

    public String getFleeceJacketPrice() {
        return driver.findElement(fleeceJacketPrice).getText();
    }

    public String getOnesiePrice() {
        return driver.findElement(onesiePrice).getText();
    }

    public String getTshirtRedPrice() {
        return driver.findElement(tshirtRedPrice).getText();
    }

    public void clickOnShoppingCartButton() {
        driver.findElement(shoppingCartButton).click();
    }

    // New method to handle item selection and price calculation
    public double addItemsToCartAndCalculateTotalPrice() {
        // Get the shuffled list of item indices
        List<Integer> shuffledIndices = Utility.getShuffledItemIndices();

        // List to store the prices of the items added to the cart
        List<Double> prices = new ArrayList<>();

        // Iterate through the shuffled indices, add items to the cart, and sum the prices
        for (int index : shuffledIndices) {
            String priceStr = null;
            switch (index) {
                case 1:
                    clickOnBackpackItem();
                    priceStr = getBackPackPrice();
                    break;
                case 2:
                    clickOnBikeLightItem();
                    priceStr = getBikeLightPrice();
                    break;
                case 3:
                    clickOnBoltTshirtItem();
                    priceStr = getBoltTshirtPrice();
                    break;
                case 4:
                    clickOnFleeceJacketItem();
                    priceStr = getFleeceJacketPrice();
                    break;
                case 5:
                    clickOnOnesieItem();
                    priceStr = getOnesiePrice();
                    break;
                case 6:
                    clickOnTshirtRedItem();
                    priceStr = getTshirtRedPrice();
                    break;
            }

            // Check if priceStr is not null before parsing
            if (priceStr != null) {
                // Remove the "$" sign before parsing
                priceStr = priceStr.substring(1);
                prices.add(Double.parseDouble(priceStr));
            }
        }

        // Calculate the total price of the added items
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }
}
