package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.tests.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
//        INITIALIZE
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//    WebElement userEmail = driver.findElement(By.id("userEmail"));
//    PAGE FACTORY
    @FindBy(id = "userEmail")
    WebElement userEmail;


    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String passwordEl){
        userEmail.sendKeys(email);
        password.sendKeys(passwordEl);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage(){

        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");

    }
}
