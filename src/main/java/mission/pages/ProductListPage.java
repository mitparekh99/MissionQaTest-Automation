package mission.pages;

import mission.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductListPage extends BasePage {

    By cartIcon = By.className("shopping_cart_link");

    public void clickOnAddProduct(String productName){  
        
        log.info("Adding product to cart: {}", productName);   
        String xpath = "add-to-cart-" + productName.toLowerCase().replace(" ", "-"); 
        WebElement addBtn = waitForElement(By.id(xpath));
        addBtn.click();

    }

    public void clickCart(){
    
       click(cartIcon);
    
    }

}