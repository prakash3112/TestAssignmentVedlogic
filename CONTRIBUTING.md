# Contributing Guide

This guide explains how to work with the test automation framework and add new test scenarios.

## Architecture Overview

The framework is organized into clear layers:

- **Pages** (`src/test/java/org/example/pages/`) — Page Object Model classes. Constructor injection of WebDriver ensures driver is initialized before pages are used.
- **Steps** (`src/test/java/org/example/steps/`) — Cucumber step definitions. Use lazy page initialization to ensure Hooks have initialized the driver first.
- **Hooks** (`src/test/java/org/example/hooks/`) — Lifecycle hooks that set up and tear down drivers and contexts.
- **API** (`src/test/java/org/example/api/`) — Low-level `ApiClient` and high-level service layer (`services/`).
- **Models** (`src/test/java/org/example/models/`) — DTOs for API request/response bodies (e.g., `UserDto`).
- **Config** (`src/test/java/org/example/config/`) — `ConfigManager` reads configuration from properties and system properties.
- **Utils** (`src/test/java/org/example/utils/`) — `DriverFactory` manages WebDriver lifecycle using ThreadLocal.

## Key Design Decisions

### 1. Page Object Initialization (Lifecycle)

**Good (do this):**
```java
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    // ... methods ...
}
```

**Bad (don't do this):**
```java
public class LoginPage extends BasePage {
    public LoginPage() {
        this.driver = DriverFactory.getDriver(); // Avoid hidden driver creation
    }
}
```

Pages must receive the WebDriver via constructor. This ensures the driver is already initialized by Hooks before pages are instantiated.

### 2. Lazy Page Initialization in Steps

**Good (do this):**
```java
public class UiSteps {
    private LoginPage loginPage;
    
    private LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(DriverFactory.getCurrentDriver());
        }
        return loginPage;
    }
    
    @Given("I am on the login page")
    public void iAmOnLoginPage() {
        loginPage().navigateToLoginPage();
    }
}
```

This ensures page objects are created **after** Hooks have initialized the driver.

### 3. API Layer Separation

**API Client** (`ApiClient.java`) — Low-level, focuses on HTTP verbs:
```java
public class ApiClient {
    public static Response get(String endpoint) { ... }
    public static Response post(String endpoint, Object body) { ... }
    public static Response put(String endpoint, Object body) { ... }
    public static Response delete(String endpoint) { ... }
}
```

**Service Layer** (`services/UserService.java`) — Business-level operations:
```java
public class UserService {
    public Response createUser(String endpoint, UserDto user) {
        return ApiClient.post(endpoint, user);
    }
    public Response updateUser(String endpoint, UserDto user) {
        return ApiClient.put(endpoint, user);
    }
}
```

**Step Definitions** — Call services, not `ApiClient` directly:
```java
@When("I create a user with name {string} and job {string}")
public void iCreateUser(String name, String job) {
    UserDto user = new UserDto(name, job);
    response = userService.createUser("/api/users", user);
}
```

### 4. Configuration Management

All configuration comes from `src/test/resources/test-config.properties` and can be overridden via `-D` system properties:

```properties
ui.base.url=https://www.saucedemo.com/
api.base.url=http://localhost:8080
wait.explicit.seconds=10
headless=true
browser=chrome
```

Use `ConfigManager` to read values:
```java
String baseUrl = ConfigManager.getUiBaseUrl();
long waitTime = ConfigManager.getExplicitWaitSeconds();
boolean headless = ConfigManager.isHeadless();
String browser = ConfigManager.getDefaultBrowser();
```

### 5. Page Object Actions — Separation of Concerns

**Good (do this):**
```java
public void enterUsername(String username) {
    type(usernameField, username);
}

public void enterPassword(String password) {
    type(passwordField, password);
}

public void clickLogin() {
    click(loginButton);
}

public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLogin();
}
```

**Bad (don't do this):**
```java
public void login(String username, String password) {
    type(usernameField, username);
    type(passwordField, password);
    click(loginButton);
    assertTrue(driver.getCurrentUrl().contains("inventory")); // Assertion in page object!
}
```

Keep actions atomic and focused. Let assertions stay in the step layer.

## Adding a New UI Test Scenario

1. **Create a feature file** (`src/test/resources/features/MyFeature.feature`):
```gherkin
@UI
Scenario: User can perform some action
  Given I am on some page
  When I perform an action
  Then the result is visible
```

2. **Create or update page objects** in `pages/`:
   - Add a new class extending `BasePage`
   - Constructor accepts `WebDriver`
   - Use `@FindBy` annotations
   - Write atomic action methods

3. **Create or update step definitions** in `steps/`:
   - Use lazy page initialization
   - Call page methods
   - Keep business logic minimal

4. **Run the test**:
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

## Adding a New API Test Scenario

1. **Create a feature file** (`src/test/resources/features/MyApiFeature.feature`):
```gherkin
@API
Scenario: API operation works
  When I send a GET request to "/api/users"
  Then the response status should be 200
```

2. **Add DTOs** if needed in `models/`:
```java
public class MyDto {
    private String field1;
    // ... getters/setters ...
}
```

3. **Add service methods** in `api/services/`:
```java
public class MyService {
    public Response myOperation(String endpoint, MyDto dto) {
        return ApiClient.post(endpoint, dto);
    }
}
```

4. **Add step definitions** in `steps/ApiSteps.java`:
```java
@When("I perform my operation")
public void iPerformOperation() {
    MyDto dto = new MyDto();
    response = myService.myOperation("/api/endpoint", dto);
}
```

5. **Run the test**:
```powershell
mvn test -Dtest=TestRunner
```

## Configuration and Execution

### Local Development (Headed Mode)

Run tests with the browser visible for debugging:
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome -Dheadless=false
```

### CI Execution (Headless Mode)

Run tests in headless mode for faster execution:
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome -Dheadless=true
```

### Cross-Browser Testing

```powershell
mvn test -Dtest=UITestRunner -Dbrowser=firefox -Dheadless=true
mvn test -Dtest=UITestRunner -Dbrowser=edge -Dheadless=true
```

### Custom Base URLs

```powershell
mvn test -Dui.base.url=https://staging.example.com
mvn test -Dapi.base.url=https://api.staging.example.com
```

## Best Practices

1. **Never call `DriverFactory.getDriver()` from page constructors** — always accept WebDriver via constructor.
2. **Use lazy initialization in steps** — don't create pages in step constructor.
3. **Prefer DTOs over raw Maps** for API request bodies.
4. **Keep page objects focused on interaction** — assertions belong in steps or a validation layer.
5. **Externalize all configuration** — no hardcoded URLs, timeouts, or browser settings.
6. **Use `ConfigManager`** to read configuration values.
7. **Make actions atomic** — separate field entry from navigation.
8. **Add meaningful JavaDoc** to page and service methods for clarity.

## Troubleshooting

### WebDriver not initialized error
**Cause:** Step constructor created page objects before Hooks ran.
**Solution:** Use lazy page initialization in step methods, not the constructor.

### Firefox binary not found
**Cause:** Firefox is not installed in the default location.
**Solution:** Install Firefox or set `webdriver.gecko.driver` system property.

### Tests fail inconsistently (flakiness)
**Cause:** Waits are too short or elements are not properly synchronized.
**Solution:** Increase `wait.explicit.seconds` in `test-config.properties` or adjust individual waits in page objects.

For more details, see the main `README.md`.

