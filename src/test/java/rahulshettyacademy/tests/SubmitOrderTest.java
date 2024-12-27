package rahulshettyacademy.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import rahulshettyacademy.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {
    static String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups= {"Purchase"})
    public static void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),  input.get("password"));
        List<WebElement> products =  productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage =  productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage =  cartPage.gotoCheckout();
        checkoutPage.selectCountry("Ind");

        ConfirmPage ConfirmPage =  checkoutPage.submitOrder();
        String confirmMessage = ConfirmPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }



//    extent reports



    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
    public void OrderHistoryTest(HashMap<String,String> input){
//        ZARA COAT 3
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        OrderPage orderPage =  productCatalogue.gotoOrderPage();
        orderPage.verifyOrderDisplay(input.get("product"));
        Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("product")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty(
                        "user.dir","//src//main//java//rahulshettyacademy//data//PurchaseOrder.json"));
        System.out.println(data);
        return new Object[][]  {{data.get(0)},{data.get(1)}};
    }

//    @DataProvider
//    public Object[][] getData() throws IOException {
////        HashMap<String,String> map = new HashMap<String,String>();
////        map.put("email","anshika@gmail.com");
////        map.put("password","Iamking@000");
////        map.put("product","ZARA COAT 3");
////
////        HashMap<String,String> map1 = new HashMap<String, String>();
////        map.put("email","shetty@gmail.com");
////        map.put("password","Iamking@000");
////        map.put("product","ADIDAS ORIGINAL");
////        return new Object[][] {{"endros@gmail.com","Endros12345","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//
//        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("//src//main//java//rahulshettyacademy//data//PurchaseOrder.json"));
//        return new Object[][]  {{data.get(0)},{data.get(1)}};
//
//
//    }
}

