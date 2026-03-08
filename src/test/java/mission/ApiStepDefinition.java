package mission;

import io.cucumber.java.en.*;
import mission.api.ReqresClient;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class ApiStepDefinition {

   ReqresClient client = new ReqresClient(); 
   Response response;
   List<Integer> userIds = new ArrayList<>();
   int totalUsers;

   @Given("I get the default list of users for on 1st page")
    public void getUsersFirstPage() {

        response = client.getUsers(1);
        response.then().statusCode(200);
        totalUsers = response.jsonPath().getInt("total");
  
    }

    @When("I get the list of all users within every page")
    public void getAllUsers() {

        int totalPages = response.jsonPath().getInt("total_pages");

        for(int page = 1; page <= totalPages; page++){

            Response pageResponse = client.getUsers(page);

            List<Integer> ids = pageResponse.jsonPath().getList("data.id");

            userIds.addAll(ids);
        }    
    }

    @Then("I should see total users count equals the number of user ids")
    public void validateTotalUsers() {
        assertEquals(userIds.size(), totalUsers);    
    }

    @Given("I make a search for user {int}")
    public void searchUser(int userID){
       
        response = client.getUsersById(userID);
    }

    @Then("I should see the following user data")
    public void validateUserInformation(io.cucumber.datatable.DataTable table){

        String expectedFirstname = table.cell(1,0);
        String expectedEmail = table.cell(1,1);

        String actualFirstName = response.jsonPath().getString("data.first_name");
        String actualEmail = response.jsonPath().getString("data.email");

        assertEquals(actualFirstName, expectedFirstname, "First name mismatch");
        assertEquals(actualEmail, expectedEmail, "Email mismatch");
        
    }

    @Then("I receive error code {int} in response")
    public void searchNotExistingUser(int expectedErrorCode){

        int actualStatusCode = response.getStatusCode(); 
        assertEquals(actualStatusCode, expectedErrorCode);

    }

    @Given("I create a user with following {string} {string}")
    public void createUser(String name, String job){

        response = client.createNewUser(name, job);
        response.prettyPrint();
      //  response.then().statusCode(201);
    

    }

    @Then("response should contain the following data")
    public void validateCreatedUser(io.cucumber.datatable.DataTable table) {

        List<String> expectedFields = table.row(0);

        for(String field : expectedFields) {

            Object value = response.jsonPath().get(field);

            assertNotNull(value, "Field missing in response: " + field);
        }

    }

    @Given("I login successfully with the following data")
    @Given("I login unsuccessfully with the following data")
    public void login(io.cucumber.datatable.DataTable table){

        String email = table.cell(1,0);
        String password = table.cell(1,1);
        response = client.login(email, password);
        response.prettyPrint();

    }

    @Then("I should get a response code of {int}")
    public void validateLoggedUserStatus(int expectedStatusCode){

        int actualStatusCode = response.getStatusCode();
        assertEquals(actualStatusCode, expectedStatusCode);

    }

    @And("I should see the following response message:")
    public void validateErrorMessage(io.cucumber.datatable.DataTable table){

        String expectedError = table.cell(0,0);
         
        expectedError = expectedError.replace("\"error\":", "")
                                     .replace("\"", "")
                                     .trim();

        String errorMessage = response.jsonPath().getString("error");

        assertEquals(errorMessage, expectedError);

    }

    @Given ("I wait for the user list to load")
    public void waitForUserList(){

        response = client.getUserDelayList();
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Then("I should see that every user has a unique id")
    public void userUniqueId(){

        List<Integer> ids = response.jsonPath().getList("data.id");

        Set<Integer> uniqueIds = new HashSet<>(ids);

        assertEquals(ids.size(), uniqueIds.size(), "Duplicate user IDs found");
    }

}
