Feature: API Tests for ReqRes

  @API
  Scenario: Get list of users
    When I send a GET request to "/api/users?page=2"
    Then the response status should be 200
    And the response should contain a list of users

  @API
  Scenario: Create a new user
    When I send a POST request to "/api/users" with body:
      | name | job  |
      | Prakash  | QA   |
    Then the response status should be 201
    And the response should contain the created user details

  @API
  Scenario: Update an existing user
    When I send a PUT request to "/api/users/2" with body:
      | name | job  |
      | Prakash  | Admin   |
    Then the response status should be 200
    And the response should contain updated user details

  @API
  Scenario: Delete a user
    When I send a DELETE request to "/api/users/2"
    Then the response status should be 204
    And the response should indicate successful deletion

