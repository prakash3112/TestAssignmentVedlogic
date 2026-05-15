# Test Automation Framework

This project implements a clean, maintainable automation framework for API and UI testing using Cucumber, Selenium, and Rest Assured.

## Structure

- `pages/`: Page Object Model classes for UI automation
- `steps/`: Cucumber step definitions
- `hooks/`: Test lifecycle hooks
- `runner/`: Test runner configuration
- `utils/`: Utility classes like DriverFactory
- `api/`: API client and services
- `models/`: Data models for API responses
- `config/`: Configuration files
- `features/`: Cucumber feature files

## Features

### UI Tests
- SauceDemo login and checkout flow
- Page Object Model with @FindBy annotations
- Centralized driver management

### API Tests
- ReqRes API user operations
- Dedicated API client layer
- Reusable request methods

## Prerequisites

- Java 11+
- Maven 3.6+

## Running Tests

### All Tests
```bash
mvn clean test
```

### UI Tests Only (Chrome Browser - UI Mode)
```bash
mvn clean test -Dcucumber.filter.tags="@UI"
```

### API Tests Only
```bash
mvn clean test -Dcucumber.filter.tags="@API"
```

### With Custom Reports
Tests automatically generate:
- **Cucumber HTML Report**: `target/cucumber-reports.html`
- **ExtentReports**: `target/ExtentReports/ExtentReport.html` (with screenshots, logs, and timeline)

## Prerequisites for Execution

1. **Java 11 Installation**:
   - Install JDK 11 from [Adoptium](https://adoptium.net/) or [Oracle](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
   - Set `JAVA_HOME` environment variable to your JDK 11 installation path

2. **Maven Installation**:
   - Download Maven from [maven.apache.org](https://maven.apache.org/download.cgi)
   - Extract to a folder (e.g., `C:\apache-maven-3.9.6`)
   - Add `C:\apache-maven-3.9.6\bin` to your system PATH environment variable
   - Verify: `mvn --version`

3. **IntelliJ Configuration** (if using IDE):
   - Go to `File > Project Structure`
   - Set `Project SDK` to JDK 11
   - Set `Project language level` to `11 - Local variable syntax for lambda parameters`
   - In `Modules`, set `Module SDK` and `Language level` to the same values
   - Reload Maven project: Right-click project → `Reload Project`
   - Rebuild: `Build > Rebuild Project`

## Design Principles

- **Page Object Model**: UI interactions encapsulated in page classes
- **Separation of Concerns**: Steps orchestrate actions, pages handle elements
- **Centralized Driver Management**: Driver lifecycle controlled in hooks
- **Reusable Components**: Common actions abstracted into base classes
- **Clean Architecture**: Clear package structure and dependency injection

## Key Improvements

- No direct Selenium calls in steps
- No hardcoded locators in step definitions
- Proper waits and synchronization
- Meaningful assertions
- Scalable for additional scenarios
