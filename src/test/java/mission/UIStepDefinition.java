package mission;

import java.util.List;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import mission.pages.CartPage;
import mission.pages.CheckoutOverviewPage;
import mission.pages.CheckoutPage;
import mission.pages.LoginPage;
import mission.pages.ProductListPage;
import org.testng.Assert;

public class UIStepDefinition extends BasePage {

    LoginPage loginPage = new LoginPage();
    ProductListPage productListPage = new ProductListPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();

    @Given("I am on the home page")
    public void openHomePage() {

        log.info("Opening application home page");
        driver.get(LoadProp.getProperty("url"));

    }

   @And("I login in with the following details")
    public void login(DataTable table) {

        List<List<String>> data = table.asLists();

        loginPage.login(
            data.get(1).get(0),
            data.get(1).get(1)
        );
    }

    @And("I add the following items to the basket")
     public void addProducts(DataTable table) {

        log.info("Adding products to cart");
        List<String> products = table.asList();    

        for(String product : products){
             productListPage.clickOnAddProduct(product);
        }
    }

    @And("I should see {int} items added to the shopping cart")
    public void verifyCount(int count) {

        int actualCount = getCartCount();
        Assert.assertEquals(actualCount, count);
    }

    @And("I click on the shopping cart")
     public void clickOnCartIcon() {
        productListPage.clickCart();
    }   

     @And("I verify that the QTY count for each item should be {int}")
        public void verifyProductQty(int expectedQty) {

        int result = cartPage.getQuantityForAllItems();
        Assert.assertEquals(result, expectedQty);
    }

    @And("I remove the following item:")
     public void removeProduct(DataTable table){

        String product = table.asList().get(0);
        cartPage.removeProduct(product);
    }
    
    @And("I click on the CHECKOUT button")
     public void clickCheckout(){
        cartPage.clickCheckout();
    }

    @And("I type {string} for First Name")
     public void enterFirstName(String value){
        checkoutPage.enterFirstName(value);
    }
    
    @And("I type {string} for Last Name")
     public void enterLastName(String value){
        checkoutPage.enterLastName(value);
    }
  
   @And("I type {string} for ZIP or Postal Code")
    public void enterZipCode(String value){
        checkoutPage.enterZipCode(value);
    }

    @When("I click on the CONTINUE button")
     public void clickContinue(){
        checkoutPage.clickContinue();
    }

    @Then("Item total will be equal to the total of items on the list")
     public void validateItemTotal(){

        double calculatedTotal = checkoutOverviewPage.getItemsSum();
        double displayedTotal = checkoutOverviewPage.getItemTotalFromPage();
        Assert.assertEquals(displayedTotal, calculatedTotal);
    }

    @And("a Tax rate of {int} % is applied to the total")
     public void validateTax(int taxRate){

        double itemTotal = checkoutOverviewPage.getItemsSum();
        double expectedTax = itemTotal * taxRate / 100;

        double displayedTax = checkoutOverviewPage.getTaxFromPage();
        Assert.assertEquals(displayedTax, expectedTax, 0.01);
    }
}
