# UI Parallel Execution & Cross-Browser Testing Guide

## Overview
This document outlines the UI parallel execution with thread group configuration and cross-browser testing implementation for the Test Automation Framework.

---

## 1. Architecture Overview

### Thread-Safe WebDriver Management
- **ThreadLocal Storage**: Each thread has its own WebDriver instance
- **Thread Safety**: No interference between parallel test executions
- **Resource Management**: Proper cleanup and disposal of WebDriver instances

```
Thread 1 (Chrome)    -> WebDriver 1 (Chrome)
Thread 2 (Firefox)   -> WebDriver 2 (Firefox)
Thread 3 (Edge)      -> WebDriver 3 (Edge)
Thread 4 (Chrome)    -> WebDriver 4 (Chrome)
```

---

## 2. Parallel Execution Configuration

### Maven Surefire Plugin Settings
Located in `pom.xml`:

```xml
<parallel>methods</parallel>          <!-- Execute tests in parallel -->
<threadCount>4</threadCount>          <!-- 4 threads -->
<forkCount>1</forkCount>              <!-- 1 JVM fork -->
<reuseForks>true</reuseForks>        <!-- Reuse JVM -->
<forkedProcessTimeoutInSeconds>300</forkedProcessTimeoutInSeconds> <!-- 5 min timeout -->
```

### Thread Group Configuration
- **Thread Count**: 4 threads for parallel execution
- **Fork Count**: 1 JVM fork (shared context)
- **Reuse Forks**: True (improves performance)
- **Timeout**: 300 seconds per test

---

## 3. Cross-Browser Testing Implementation

### Supported Browsers
1. **Google Chrome** (@Chrome tag)
   - Default browser
   - Optimized for performance
   
2. **Mozilla Firefox** (@Firefox tag)
   - Full feature support
   - Separate profile management
   
3. **Microsoft Edge** (@Edge tag)
   - Chromium-based
   - Modern testing capabilities

### Browser Tags in Feature Files
```gherkin
@UI @Chrome
Scenario: Login to SauceDemo on Chrome

@UI @Firefox
Scenario: Login to SauceDemo on Firefox

@UI @Edge
Scenario: Login to SauceDemo on Edge
```

---

## 4. DriverFactory Enhancement

### Key Methods
```java
// Get WebDriver with browser specification
public static WebDriver getDriver(String browser)

// Get current WebDriver instance
public static WebDriver getCurrentDriver()

// Get current browser name
public static String getCurrentBrowser()

// Quit WebDriver safely
public static void quitDriver()

// Check if driver is initialized
public static boolean isDriverInitialized()
```

### Thread-Local Implementation
```java
private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
private static ThreadLocal<String> currentBrowser = new ThreadLocal<>();
```

---

## 5. Hooks Configuration

### Enhanced Setup Hook
```java
@Before("@UI")
public void setUp(Scenario scenario) {
    // Get browser from tags or system property
    browser = System.getProperty("browser", "chrome");
    
    if (scenario.getSourceTagNames().contains("@Chrome")) {
        browser = "chrome";
    } else if (scenario.getSourceTagNames().contains("@Firefox")) {
        browser = "firefox";
    } else if (scenario.getSourceTagNames().contains("@Edge")) {
        browser = "edge";
    }
    
    // Initialize WebDriver
    DriverFactory.getDriver(browser);
}
```

### Enhanced Teardown Hook
```java
@After("@UI")
public void tearDown(Scenario scenario) {
    DriverFactory.quitDriver();
}
```

---

## 6. ParallelExecutionConfig Class

### Features
- Thread group management
- Browser assignment to threads
- System property access
- Thread information logging

### Usage Example
```java
// Get thread group for current thread
ThreadGroup group = ParallelExecutionConfig.getThreadGroup();

// Get assigned browser for thread
Browser browser = ParallelExecutionConfig.getBrowserForThread();

// Log thread information
ParallelExecutionConfig.logThreadInfo();
```

---

## 7. Test Runners

### APITestRunner (TestRunner.java)
- Executes tests with @API tag
- Focused on API testing
- Parallel execution enabled

```java
@CucumberOptions(
    tags = "@API",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/api-report.html",
        "json:target/cucumber-reports/api-report.json"
    }
)
```

### UITestRunner.java
- Executes tests with @UI tag
- Supports cross-browser testing
- Parallel execution enabled
- Enhanced reporting

```java
@CucumberOptions(
    tags = "@UI",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/ui-report.html",
        "json:target/cucumber-reports/ui-report.json",
        "junit:target/cucumber-reports/ui-report.xml"
    }
)
```

---

## 8. Running Tests

### Run All UI Tests (Parallel)
```bash
mvn clean test -Dtest=UITestRunner
```

### Run Specific Browser Tests
```bash
# Chrome tests only
mvn clean test -Dcucumber.filter.tags="@UI and @Chrome"

# Firefox tests only
mvn clean test -Dcucumber.filter.tags="@UI and @Firefox"

# Edge tests only
mvn clean test -Dcucumber.filter.tags="@UI and @Edge"
```

### Run API Tests
```bash
mvn clean test -Dtest=TestRunner
```

### Run All Tests (API + UI)
```bash
mvn clean test
```

### Windows Batch Script
```batch
run-ui-parallel-tests.bat
```

### Unix/Linux Shell Script
```bash
./run-ui-parallel-tests.sh
```

---

## 9. UI Test Scenarios

### Implemented Scenarios

#### 1. Login Tests (Cross-Browser)
- **Chrome**: Login to SauceDemo on Chrome
- **Firefox**: Login to SauceDemo on Firefox
- **Edge**: Login to SauceDemo on Edge

#### 2. Checkout Tests (Cross-Browser)
- **Chrome**: Add item to cart and checkout
- **Firefox**: Add item to cart and checkout
- **Edge**: Add item to cart and checkout

#### 3. Inventory Tests (Parallel)
- **Chrome**: Verify inventory items
- **Firefox**: Verify inventory items
- **Edge**: Verify inventory items

### Feature File Location
`src/test/resources/features/UI-Test.feature`

---

## 10. Execution Flow Diagram

```
┌─────────────────────────────────────────────────────────┐
│          Maven Test Execution                            │
└──────────────────┬──────────────────────────────────────┘
                   │
       ┌───────────┴───────────┐
       │                       │
    @API Tags               @UI Tags
       │                       │
  ┌─────────┐         ┌────────┴────────┐
  │TestRunner│     ┌────┴─────┬────┴─────┐
  └─────────┘     @Chrome  @Firefox   @Edge
       │            │        │         │
   4 Parallel    Thread 1  Thread 2  Thread 3
   Threads       Chrome    Firefox   Edge
       │            │        │         │
       │      ┌──────┴────────┴─────────┘
       │      │
       └──────┴─── Sequential/Parallel Execution
              │
         ┌────┴────┐
    Results    Reports
```

---

## 11. Key Features

### ✅ Thread Safety
- ThreadLocal WebDriver storage
- No shared state between threads
- Safe resource cleanup

### ✅ Cross-Browser Support
- Chrome, Firefox, Edge
- Dynamic browser selection
- Feature file tags for browser assignment

### ✅ Parallel Execution
- 4 concurrent threads
- 1 JVM fork with reuse
- Optimized for CI/CD environments

### ✅ Comprehensive Logging
- Thread ID and name logging
- Browser initialization tracking
- Test execution timestamps

### ✅ Flexible Configuration
- System property support
- Tag-based browser selection
- Environment-specific settings

### ✅ Robust Error Handling
- Exception handling in driver quit
- ThreadLocal cleanup
- Safe resource disposal

---

## 12. Performance Optimization

### Browser Options
```java
// Chrome Performance Options
--disable-gpu
--no-sandbox
--disable-blink-features=AutomationControlled

// Firefox Performance Options
dom.webnotifications.enabled = false

// Edge Performance Options
--disable-gpu
--no-sandbox
```

### Execution Time Estimation
- Sequential: 4 scenarios × 30 seconds = 120 seconds
- Parallel (4 threads): 30 seconds (4x faster)

---

## 13. Troubleshooting

### Issue: WebDriver Not Initialized
**Solution**: Check Hooks.java setup method is called before test execution

### Issue: Thread Timeout
**Solution**: Increase timeout in pom.xml `forkedProcessTimeoutInSeconds`

### Issue: Browser Crash During Parallel Execution
**Solution**: Ensure sufficient system resources for multiple browser instances

### Issue: Report Generation Issues
**Solution**: Verify target/cucumber-reports directory exists and is writable

---

## 14. Best Practices

1. **Use ThreadLocal for WebDriver**
   - Prevents thread interference
   - Ensures test isolation

2. **Browser Tag Consistency**
   - Use @Chrome, @Firefox, @Edge consistently
   - Always use @UI tag

3. **Resource Management**
   - Always call quitDriver() in teardown
   - Use try-finally for safety

4. **Test Data Isolation**
   - Use incognito/private mode
   - Clear browser cache between tests

5. **Error Handling**
   - Log thread information for debugging
   - Capture screenshots on failure

---

## 15. Summary

| Feature | Status | Details |
|---------|--------|---------|
| Parallel Execution | ✅ | 4 threads configured |
| Cross-Browser | ✅ | Chrome, Firefox, Edge |
| Thread Groups | ✅ | 4 groups configured |
| Thread Safety | ✅ | ThreadLocal implementation |
| UI Scenarios | ✅ | 9 scenarios total |
| API Scenarios | ✅ | 4 scenarios total |
| Reporting | ✅ | HTML, JSON, XML formats |

---

Generated: May 18, 2026
Last Updated: May 18, 2026

