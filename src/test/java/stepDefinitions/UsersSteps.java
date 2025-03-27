package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class UsersSteps {

    private Response response;
    private JSONObject requestBody;

    @Given("the API is available")
    public void the_api_is_available() {
        baseURI = "https://reqres.in";
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given().when().get(endpoint);
    }

    @When("I send a POST request to {string} with the following data:")
    public void i_send_a_post_request_to_with_data(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        requestBody = new JSONObject();
        for (Map<String, String> map : data) {
            requestBody.put("name", map.get("name"));
            requestBody.put("job", map.get("job"));
        }

        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post(endpoint);
    }

    @When("I send a PUT request to {string} with the following data:")
    public void i_send_a_put_request_to_with_data(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        requestBody = new JSONObject();
        for (Map<String, String> map : data) {
            requestBody.put("name", map.get("name"));
            requestBody.put("job", map.get("job"));
        }

        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .body(requestBody.toString()) // Ensure org.json.JSONObject is imported
                .when()
                .put(endpoint);
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        response = given().when().delete(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the response should contain {string} as {string}")
    public void the_response_should_contain_key_as_value(String key, String expectedValue) {
        assertEquals(expectedValue, response.jsonPath().getString(key));
    }

    @Then("the response should contain user details")
    public void the_response_should_contain_user_details() {
        assertNotNull(response.jsonPath().get("data"));
    }
}
