package mission.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import mission.BasePage;

public class CheckoutOverviewPage extends BasePage {

    By itemPrices = By.className("inventory_item_price");
    By itemTotal = By.className("summary_subtotal_label");
    By taxLabel = By.className("summary_tax_label");

    public double getItemsSum(){

        List<WebElement> prices = driver.findElements(itemPrices);

        double total = 0;

        for(WebElement price : prices){

            String value = price.getText().replace("$","");
            total += Double.parseDouble(value);
        }

        return total;
    }

    public double getItemTotalFromPage(){

        String text = driver.findElement(itemTotal).getText();
        String value = text.split("\\$")[1];
        return Double.parseDouble(value);
        
    }

    public double getTaxFromPage(){

        String text = driver.findElement(taxLabel).getText();
        String value = text.split("\\$")[1];
        return Double.parseDouble(value);

    }
}