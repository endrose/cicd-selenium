package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class StandAloneTest {

    public static void main(String[] args) {
//        System.setProperty("webdriver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriverManager.chromedriver();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/client/");

        driver.findElement(By.id("userEmail")).sendKeys("endros@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Endros12345");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(5));
        String productName = "ZARA COAT 3";
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod =   products.stream().filter(product ->
                        product.findElement(By.cssSelector("b"))
                                .getText()
                                .equals(productName))
                .findFirst().orElse(null);


        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
//    ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerLink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        boolean match =  cartProducts.stream().anyMatch(cartProduct -> cartProduct
                .getText().equalsIgnoreCase(productName) );
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"Ind").build().perform();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[3]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String  confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }
}