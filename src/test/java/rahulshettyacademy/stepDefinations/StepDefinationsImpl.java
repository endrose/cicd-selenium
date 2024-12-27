package rahulshettyacademy.stepDefinations;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.List;

import static rahulshettyacademy.TestComponents.BaseTest.driver;
import static rahulshettyacademy.TestComponents.BaseTest.launchApplication;


public class StepDefinationsImpl {
   public LandingPage landingPage;
   public ProductCatalogue productCatalogue;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        //
       landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password){
       productCatalogue =   landingPage.loginApplication(username, password);
    }
    @When("^I add product (.+) to cart to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products =  productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_the_order(String productName){
        CartPage cartPage =  productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage =  cartPage.gotoCheckout();
        checkoutPage.selectCountry("Ind");
        ConfirmPage confirmPage =  checkoutPage.submitOrder();

    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string){
        String confirmMessage = ConfirmPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String string) throws Throwable{
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }

}
