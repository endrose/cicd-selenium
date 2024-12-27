package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.tests.AbstractComponents.AbstractComponent;

public class ConfirmPage extends AbstractComponent {
    static WebDriver driver;

    public ConfirmPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    static WebElement confirmationPage;

    public static String getConfirmMessage(){

        CheckoutPage cp = new CheckoutPage(driver);
        cp.submit.click();
        return confirmationPage.getText();
    }

}
