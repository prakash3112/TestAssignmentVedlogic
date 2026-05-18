package org.example.steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.example.api.ApiClient;
import java.util.Map;
import static org.junit.Assert.*;

public class ApiSteps {
    private Response response;

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = ApiClient.get(endpoint);


    }

    @When("I send a POST request to {string} with body:")
    public void iSendAPOSTRequestToWithBody(String endpoint, Map<String, String> body) {
        response = ApiClient.post(endpoint, body);
    }

    @When("I send a PUT request to {string} with body:")
    public void iSendAPUTRequestToWithBody(String endpoint, Map<String, String> body) {
        response = ApiClient.put(endpoint, body);
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        response = ApiClient.delete(endpoint);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of users")
    public void theResponseShouldContainAListOfUsers() {
        assertNotNull(response.jsonPath().getList("data"));
        assertTrue(response.jsonPath().getList("data").size() > 0);
    }

    @Then("the response should contain the created user details")
    public void theResponseShouldContainTheCreatedUserDetails() {
        assertNotNull(response.jsonPath().getString("name"));
        assertNotNull(response.jsonPath().getString("job"));
        assertNotNull(response.jsonPath().getString("id"));
        assertNotNull(response.jsonPath().getString("createdAt"));
    }

    @Then("the response should contain updated user details")
    public void theResponseShouldContainUpdatedUserDetails() {
        assertNotNull(response.jsonPath().getString("name"));
        assertNotNull(response.jsonPath().getString("job"));
        assertNotNull(response.jsonPath().getString("updatedAt"));
    }

    @Then("the response should indicate successful deletion")
    public void theResponseShouldIndicateSuccessfulDeletion() {
        assertEquals(204, response.getStatusCode());
    }
}
