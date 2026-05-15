Feature: UI Tests for SauceDemo

  @UI
  Scenario: Login to SauceDemo
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be logged in successfully

  @UI
  Scenario: Add item to cart and checkout
    Given I am logged in to SauceDemo
    When I add the first item to the cart
    And I proceed to checkout
    And I fill in checkout information with "Prakash" "Patel" "12345"
    Then the checkout should be successful
