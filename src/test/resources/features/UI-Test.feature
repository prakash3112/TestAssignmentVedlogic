Feature: UI Tests for SauceDemo
  Parallel Execution with Cross-Browser Support

  @UI @Chrome
  Scenario: Login to SauceDemo on Chrome
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be logged in successfully

  @UI @Firefox
  Scenario: Login to SauceDemo on Firefox
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be logged in successfully

  @UI @Edge
  Scenario: Login to SauceDemo on Edge
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be logged in successfully

  @UI @Chrome
  Scenario: Add item to cart and checkout on Chrome
    Given I am logged in to SauceDemo
    When I add the first item to the cart
    And I proceed to checkout
    And I fill in checkout information with "Prakash" "Patel" "12345"
    Then the checkout should be successful

  @UI @Firefox
  Scenario: Add item to cart and checkout on Firefox
    Given I am logged in to SauceDemo
    When I add the first item to the cart
    And I proceed to checkout
    And I fill in checkout information with "Prakash" "Patel" "12345"
    Then the checkout should be successful

  @UI @Edge
  Scenario: Add item to cart and checkout on Edge
    Given I am logged in to SauceDemo
    When I add the first item to the cart
    And I proceed to checkout
    And I fill in checkout information with "Prakash" "Patel" "12345"
    Then the checkout should be successful

  @UI @Chrome @Parallel
  Scenario: Verify inventory items on Chrome
    Given I am logged in to SauceDemo
    Then the inventory page should display items

  @UI @Firefox @Parallel
  Scenario: Verify inventory items on Firefox
    Given I am logged in to SauceDemo
    Then the inventory page should display items

  @UI @Edge @Parallel
  Scenario: Verify inventory items on Edge
    Given I am logged in to SauceDemo
    Then the inventory page should display items

