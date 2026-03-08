package mission.api;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import mission.BasePage;

public class ReqresClient extends BasePage{

    public Response getUsers(int page) {


        log.info("Calling API: GET /users?page={}", page);
        return ApiConfig.request()
                .when()
                .get("/users?page=" + page);
    }

    public Response getUsersById(int userID) {

        return ApiConfig.request()
                .when()
                .get("/users/" + userID);
        }

    public Response createNewUser(String name, String job) {
      
            Map<String, String> body =  new HashMap<>();

            body.put("name", name);
            body.put("job", job);

            return ApiConfig.request()
                    .body(body)
                    .when()
                    .post("/users");
        }

    public Response login(String email, String password) {

        Map<String, String> body = new HashMap<>();

        body.put("email", email);
        body.put("password", password);

        return ApiConfig.request()
                    .body(body)
                    .when()
                    .post("/login");
        }    

    public Response getUserDelayList(){

        return ApiConfig.request()
                .when()
                .get("/users?delay=3");
        }    

}

