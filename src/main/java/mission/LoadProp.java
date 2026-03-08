package mission;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProp extends BasePage {

    static Properties prop = new Properties();
    static InputStream input;
    public static String testData = "src/test/TestData/TestData.properties";

    public static String getProperty(String key) {
       
        try {
            input = LoadProp.class.getClassLoader().getResourceAsStream("TestData.properties");
            prop.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
