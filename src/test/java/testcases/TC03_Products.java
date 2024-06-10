package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P04_ProductsPage;
import util.Utility;

public class TC03_Products extends TestBase {

    P04_ProductsPage productsPage;



    @Test
    public void CheckOutProducts() throws InterruptedException {
        int number_Of_Products = Utility.generateUniqueRandomNumbers(3).get(0);
        System.out.println(number_Of_Products);
        productsPage = new P04_ProductsPage(driver);
        productsPage.addRandomProducts(number_Of_Products);
    }

    @Test
    public void testAddAndRemoveProducts() throws InterruptedException {
        int initialCount = 3;
        productsPage = new P04_ProductsPage(driver);
        productsPage.addRandomProducts(initialCount);
        Assert.assertTrue(productsPage.verifyProductsSelected(), "Initial products not selected correctly");

        productsPage.removeRandomProduct();
        Assert.assertEquals(productsPage.verifyProductsSelected(), initialCount - 1, "Product removal failed");
    }
}

//package testcases;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.P04_ProductsPage;
//import util.Utility;
//
//public class TC03_Products extends TestBase {
//
//    P04_ProductsPage productsPage;
//
//    @Test
//    public void CheckOutProducts() throws InterruptedException {
//        int number_Of_Products = Utility.generateUniqueRandomNumbers(3).get(0);
//        System.out.println(number_Of_Products);
//        new P04_ProductsPage(driver).addRandomProducts(number_Of_Products);
//    }
//
//    @Test
//    public void testAddAndRemoveProducts() throws InterruptedException {
//        int initialCount = 3;
//        productsPage.addRandomProducts(initialCount);
//        Assert.assertTrue(productsPage.verifyProductsSelected(), "Initial products not selected correctly");
//
//        productsPage.removeRandomProduct();
//        Assert.assertEquals(productsPage.verifyProductsSelected(), initialCount - 1, "Product removal failed");
//    }
//
//}
