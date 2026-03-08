package mission.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import mission.LoadProp;

public class ApiConfig {

    private static String BASE_URL = LoadProp.getProperty("API_BASE_URL");
    private static  String API_KEY = LoadProp.getProperty("API_KEY");
   
     public static RequestSpecification request() {

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("x-api-key", API_KEY)
                .contentType("application/json");
     }

}
