package mission.pages;

import mission.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    By cartItemsQty = By.className("cart_quantity");
    By checkoutBtn = By.id("checkout");

    public int getQuantityForAllItems(){

        List<WebElement> qtyList = driver.findElements(cartItemsQty);

        for(WebElement qty : qtyList){

            String quantity = qty.getText();

            if(!quantity.equals("1")){
                return 0;   // means validation failed
            }
        }

        return 1; // all quantities are 1
    }

    public void clickCheckout(){
        click(checkoutBtn);
    }

    public void removeProduct(String productName){

        String xpath = "//div[text()='" + productName +"']/ancestor::div[@class='cart_item']//button";
        driver.findElement(By.xpath(xpath)).click();

    }

}