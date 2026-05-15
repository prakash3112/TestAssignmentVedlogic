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
      | Jane  | admin   |
    Then the response status should be 201
    And the response should contain the created user details
