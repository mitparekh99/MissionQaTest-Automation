package mission;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;

public class BasePage {

    public static WebDriver driver;
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    By cartBadge = By.className("shopping_cart_badge");
    By sideMenu = By.id("react-burger-menu-btn");
    By resertApp = By.id("reset_sidebar_link");

    public WebElement find(By locator){
        return driver.findElement(locator);
    }

     public void click(By locator){
        log.info("Clicking element: {}", locator);
        find(locator).click();
    }

    public void type(By locator, String text){
        log.info("Typing '{}' into {}", text, locator);
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    public String getText(By locator){
        return find(locator).getText();
    }

    public int getCartCount(){

        String badgeCount = driver.findElement(cartBadge).getText();
        return Integer.parseInt(badgeCount);

    }

    public void resetAppState(){

        driver.findElement(sideMenu).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(resertApp))
                .click();
        driver.navigate().refresh();

    }

    public WebElement waitForElement(By locator){

         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
