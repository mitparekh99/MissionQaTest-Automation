package mission.pages;

import org.openqa.selenium.By;
import mission.BasePage;

public class LoginPage extends BasePage {

    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");

    public void login(String user, String pass){
       
        log.info("Logging into application with user: {}", user);

        type(username, user);
        type(password, pass);
        click(loginBtn);
        resetAppState(); 
    }
    
}
