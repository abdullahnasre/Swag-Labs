package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutOverview {
    WebDriver driver;
    private final By total = By.xpath("//div[@class='summary_total_label']");
    private final By finishButton = By.xpath("//a[@class='btn_action cart_button']");
    private final By taxLabel = By.xpath("//div[@class='summary_tax_label']");

    public CheckOutOverview(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotal() {
        return driver.findElement(total).getText();
    }

    public String getTax() {
        return driver.findElement(taxLabel).getText();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    // New method to fetch and compare the total price
    public double getActualTotalPrice() {
        String totalStr = getTotal().replace("Total: $", "");
        double actualTotalPrice = Double.parseDouble(totalStr);
        System.out.println("Actual total price: $" + actualTotalPrice);
        return actualTotalPrice;
    }

    // New method to round a price to two decimal places
    public static double roundToTwoDecimalPlaces(double price) {
        return Math.round(price * 100.0) / 100.0;
    }
}
