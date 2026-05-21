# API PUT and DELETE Implementation Summary



1. Cucumber Feature File Updates

File: `src/test/resources/features/API-Test.feature`


**1.1 Update an Existing User (PUT)**
```gherkin
@API
Scenario: Update an existing user
  When I send a PUT request to "/api/users/2" with body:
    | name | job  |
    | Prakash  | Admin   |
  Then the response status should be 200
  And the response should contain updated user details
```

**1.2 Delete a User (DELETE)**
```gherkin
@API
Scenario: Delete a user
  When I send a DELETE request to "/api/users/2"
  Then the response status should be 204
  And the response should indicate successful deletion
```

---

## 2. API Client Implementation

### File: `src/test/java/org/example/api/ApiClient.java`

#### Added Methods:

**2.1 PUT Method**
```java
public static Response put(String endpoint, Map<String, String> body) {
    return RestAssured.given()
            .header("Content-Type", "application/json")
            .body(body)
            .put(endpoint);
}
```

**2.2 DELETE Method**
```java
public static Response delete(String endpoint) {
    return RestAssured.given()
            .delete(endpoint);
}
```

---

## 3. Step Definitions Implementation

### File: `src/test/java/org/example/steps/ApiSteps.java`

#### Added Step Definitions:

**3.1 PUT Request Step**
```java
@When("I send a PUT request to {string} with body:")
public void iSendAPUTRequestToWithBody(String endpoint, Map<String, String> body) {
    response = ApiClient.put(endpoint, body);
}
```

**3.2 DELETE Request Step**
```java
@When("I send a DELETE request to {string}")
public void iSendADELETERequestTo(String endpoint) {
    response = ApiClient.delete(endpoint);
}
```

**3.3 Updated User Details Assertion**
```java
@Then("the response should contain updated user details")
public void theResponseShouldContainUpdatedUserDetails() {
    assertNotNull(response.jsonPath().getString("name"));
    assertNotNull(response.jsonPath().getString("job"));
    assertNotNull(response.jsonPath().getString("updatedAt"));
}
```

**3.4 Successful Deletion Assertion**
```java
@Then("the response should indicate successful deletion")
public void theResponseShouldIndicateSuccessfulDeletion() {
    assertEquals(204, response.getStatusCode());
}
```

---

## 4. Page Objects Enhancement

### File: `src/test/java/org/example/pages/CartPage.java`

**4.1 Added Cart Item Count Method**
```java
@FindBy(css = ".cart_item")
private List<WebElement> cartItems;

public int getCartItemCount() {
    return cartItems.size();
}
```

### File: `src/test/java/org/example/pages/InventoryPage.java`

**4.2 Added Inventory Item Count Method**
```java
@FindBy(css = ".inventory_item")
private List<WebElement> inventoryItems;

public int getInventoryItemCount() {
    return inventoryItems.size();
}
```

---

## 5. API Test Scenarios Summary

### Complete API Test Coverage:

| Scenario | Method | Endpoint | Expected Status | Assertions |
|----------|--------|----------|-----------------|-----------|
| Get list of users | GET | /api/users?page=2 | 200 | List size > 0 |
| Create a new user | POST | /api/users | 201 | name, job, id, createdAt |
| Update an existing user | PUT | /api/users/2 | 200 | name, job, updatedAt |
| Delete a user | DELETE | /api/users/2 | 204 | Status code = 204 |

---

## 6. Running the Tests

### Run Only API Tests:
```bash
mvn clean test -Dcucumber.filter.tags="@API"
```

### Run Specific Scenario:
```bash
mvn clean test -Dcucumber.filter.tags="@API" -Dcucumber.filter.name="Update an existing user"
```

### Run All Tests with Parallel Execution:
```bash
mvn clean test
```

---

## 7. Test Execution Notes

- All PUT and DELETE endpoints are tested against the ReqRes API (https://reqres.in)
- Tests include proper assertions for response validation
- Dynamic test data is passed through Cucumber tables
- Cross-browser execution is supported via DriverFactory
- Parallel execution is configured with 4 threads in pom.xml

---

## 8. Implementation Checklist

- ✅ Added PUT request method to ApiClient
- ✅ Added DELETE request method to ApiClient
- ✅ Created PUT scenario in API-Test.feature
- ✅ Created DELETE scenario in API-Test.feature
- ✅ Implemented PUT step definition
- ✅ Implemented DELETE step definition
- ✅ Added assertion for updated user details
- ✅ Added assertion for successful deletion
- ✅ Fixed CartPage with item count method
- ✅ Fixed InventoryPage with item count method
- ✅ Compiled successfully without errors

---

## 9. Key Features

1. **RESTful API Testing**: Complete CRUD operations (Create, Read, Update, Delete)
2. **Dynamic Test Data**: Uses Cucumber tables for flexible test inputs
3. **Comprehensive Assertions**: Validates response status codes and content
4. **Parallel Execution**: Configured for 4 parallel threads
5. **Cross-Browser Support**: Supports Chrome, Firefox, and Edge
6. **BDD Framework**: Full Behavior-Driven Development approach with Cucumber

---

Generated: May 18, 2026

