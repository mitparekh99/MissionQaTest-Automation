package mission;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.Screenshot;

public class Hook extends BasePage {

    BrowserSetup browsersetup = new BrowserSetup();
    private static final int WAIT_SEC = 20;


    @Before("@ui")
    public void initializeTest(Scenario scenario) {

        log.info("====================================");
        log.info("Starting Scenario: {}", scenario.getName());
        log.info("====================================");

        browsersetup.selectBrowser();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_SEC));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SEC));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(WAIT_SEC));
        
    }

    @After("@ui")
    public void tearDown(Scenario scenario){

        log.info("Finished Scenario: {}", scenario.getName());
        
        if (scenario.isFailed()) {

        String screenShotFilename = scenario.getName().replace(" ", "")
                + new Timestamp(new Date().getTime()).toString().replaceAll("[^a-zA-Z0-9]", "")
                + "_" + LoadProp.getProperty("Browser") + ".jpg";
        
        Screenshot screenshot = new AShot()
            .shootingStrategy(ShootingStrategies.viewportPasting(100))
            .takeScreenshot(driver);
                
        BufferedImage image = screenshot.getImage();        

        File scrFile = new File(LoadProp.getProperty("ScreenshotLocation") + screenShotFilename);

        try {
             ImageIO.write(image, "PNG", scrFile);
            log.info("Screenshot saved: {}", screenShotFilename);
            } catch (IOException e) {
                 log.error("Failed to save screenshot", e);   
        }
    }

    // Always close browser
    if (driver != null) {
        driver.quit();
    }
 }

    @Before("@api")
    public void apiSetup(Scenario scenario) {

        log.info("Starting API Scenario: {}", scenario.getName());
        
    } 
}
