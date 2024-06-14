package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Utility.generateUniqueRandomNumbers;
import static util.Utility.parsePriceFromString;


public class P04_ProductsPage {
    static WebDriver driver;

    public static float total;

    public P04_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    static int temp;

    public static P04_ProductsPage addRandomProducts(int count) throws InterruptedException {
        temp = count;
        List<Integer> uniqueRandomNumbers = generateUniqueRandomNumbers(count);
        total = 0;
        // 1,4,6,2,3
        for (int i = 1; i <= count; i++) {
            // click "add to card"
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[@class=\"btn_primary btn_inventory\"])[" + uniqueRandomNumbers.get(i - 1) + "]")).click();
            // store price
            total += parsePriceFromString(driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[" + uniqueRandomNumbers.get(i - 1) + "]")).getText());
        }
        System.out.println("Total Price is :" + total);
        Thread.sleep(5000);
//        return this;
        return null;
    }

    public static P04_ProductsPage removeRandomProduct() throws InterruptedException {
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[text()='Remove']"));
        if (removeButtons.size() > 0) {
            int randomIndex = (int) (Math.random() * removeButtons.size());
            WebElement removeButton = removeButtons.get(randomIndex);
            String priceStr = driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[" + (randomIndex + 1) + "]")).getText();
            priceStr = priceStr.substring(1); // Remove "$"
            double price = Double.parseDouble(priceStr);
            total -= price;

            removeButton.click();
            System.out.println("Removed item price: $" + price);
            System.out.println("New total price: $" + total);
        }

        return null;
    }

    public static boolean verifyProductsSelected() {
        return driver.findElements(By.xpath("//button[text()='Remove']")).size() == temp;
    }
}



