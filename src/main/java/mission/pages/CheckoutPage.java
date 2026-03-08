package mission.pages;

import mission.BasePage;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By zipCode = By.id("postal-code");
    By continueBtn = By.id("continue");

    public void enterFirstName(String value){
        type(firstName, value);
    }

    public void enterLastName(String value){
        type(lastName, value);
    }

    public void enterZipCode(String value){
        type(zipCode, value);
    }

    public void clickContinue(){
        click(continueBtn);
    }
}