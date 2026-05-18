# Maven Command Line Reference - UI & API Testing

**Last Updated**: May 18, 2026  
**Framework**: Selenium + Cucumber + Maven  

---

## 🚀 Quick Command Reference

### UI Tests
```bash
mvn test -Dtest=UITestRunner                           # All browsers
mvn test -Dtest=UITestRunner -Dbrowser=chrome          # Chrome only
mvn test -Dtest=UITestRunner -Dbrowser=firefox         # Firefox only
mvn test -Dtest=UITestRunner -Dbrowser=edge            # Edge only
```

### API Tests
```bash
mvn test -Dtest=TestRunner                             # Run API tests
```

### Combined Tests
```bash
mvn verify                                              # All UI + API tests
mvn clean verify                                        # Clean + all tests
```

---

## 📋 Complete Maven Commands

### 1. UI Test Execution

#### Run All UI Tests (Chrome, Firefox, Edge - Parallel)
```bash
mvn test -Dtest=UITestRunner
```
**Output**: HTML, JSON, XML reports in target/cucumber-reports/

#### Run Chrome Tests Only (Headless Mode)
```bash
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```
**Time**: ~30-45 seconds  
**Mode**: Headless (no display)

#### Run Firefox Tests Only (Headless Mode)
```bash
mvn test -Dtest=UITestRunner -Dbrowser=firefox
```
**Requires**: Firefox installed (`choco install firefox -y`)

#### Run Edge Tests Only (Headless Mode)
```bash
mvn test -Dtest=UITestRunner -Dbrowser=edge
```
**Requires**: Edge installed (`choco install microsoft-edge -y`)

#### Run UI Tests with Custom Configuration
```bash
mvn test -Dtest=UITestRunner -Dthreads=2
```

---

### 2. API Test Execution

#### Run All API Tests
```bash
mvn test -Dtest=TestRunner
```

#### Run Specific API Test Scenario
```bash
mvn test -Dtest=TestRunner#"Get list of users"
```

---

### 3. Combined Test Execution

#### Run ALL Tests (UI + API, Parallel)
```bash
mvn verify
```
**What Runs**:
- All UI test scenarios (Chrome, Firefox, Edge)
- All API test scenarios
- 4 parallel threads
- Generates: HTML, JSON, XML reports

#### Run Complete Test Suite with Clean Build
```bash
mvn clean verify
```
**Includes**:
- Clean previous build
- Compile source code
- Compile test code
- Run all tests
- Generate reports

#### Run with Verbose/Debug Output
```bash
mvn verify -X                    # Debug mode
mvn verify -e                    # Show errors
mvn verify -X -e                 # Both debug and errors
```

#### Run with Modified Parallelization
```bash
mvn verify -Dthreads=8           # Use 8 threads
mvn verify -Dthreads=1           # Sequential (single thread)
```

---

### 4. Build Operations

#### Clean Build Directory
```bash
mvn clean
```

#### Compile Source Code Only
```bash
mvn compile
```

#### Compile Test Code Only
```bash
mvn test-compile
```

#### Install Dependencies
```bash
mvn clean install
```

#### Install without Running Tests
```bash
mvn clean install -DskipTests
```

---

### 5. Report Generation

#### Generate HTML Report
```bash
mvn test -Dtest=UITestRunner
# Report: target/cucumber-reports/ui-report.html
```

#### Generate All Report Formats
```bash
mvn verify
# Generates:
#   - target/cucumber-reports/ui-report.html
#   - target/cucumber-reports/ui-report.json
#   - target/cucumber-reports/ui-report.xml
```

#### View HTML Report
```powershell
# Windows
start target\cucumber-reports\ui-report.html

# macOS
open target/cucumber-reports/ui-report.html

# Linux
xdg-open target/cucumber-reports/ui-report.html
```

---

### 6. Dependency Management

#### Update Dependencies
```bash
mvn dependency:update-snapshots
```

#### Check Dependency Tree
```bash
mvn dependency:tree
```

#### List Dependency Conflicts
```bash
mvn dependency:analyze
```

---

### 7. Troubleshooting Commands

#### Skip Tests During Build
```bash
mvn install -DskipTests
```

#### Run Single Test Class
```bash
mvn test -Dtest=UITestRunner
mvn test -Dtest=TestRunner
```

#### Run Specific Test Method
```bash
mvn test -Dtest=UITestRunner#testLoginScenario
```

#### Fail Build on Test Failure
```bash
mvn verify -Dmaven.test.failure.ignore=false
```

#### Continue Build Despite Test Failures
```bash
mvn verify -Dmaven.test.failure.ignore=true
```

#### Increase Test Timeout
```bash
mvn verify -Dsome.java.option=10000
```

---

## 🔧 Advanced Usage

### Run Tests with Different Settings

#### Run with Different Thread Count
```bash
mvn test -Dtest=UITestRunner -DthreadCount=8
```

#### Run with System Properties
```bash
mvn test -Dtest=UITestRunner -Dbrowser=chrome -Dheadless=true
```

#### Run with Maven Profiles
```bash
mvn test -Pui-tests
mvn test -Papi-tests
mvn test -Pall-tests
```

#### Run Tests in Fork Mode
```bash
mvn test -DforkCount=2
```

#### Set Test Timeout (in seconds)
```bash
mvn test -DforkedProcessTimeoutInSeconds=600
```

---

## 📊 Performance Optimization

### Run Tests in Parallel (Faster)
```bash
mvn verify -DthreadCount=4           # 4 parallel threads (default)
mvn verify -DthreadCount=8           # 8 parallel threads
mvn verify -DthreadCount=1           # Sequential execution
```

### Run Only Failing Tests
```bash
mvn test -Dtest=failsafe-tests
```

### Run Tests with Coverage
```bash
mvn clean verify jacoco:report
```

---

## 🎯 Common Use Cases

### Case 1: I want to run Chrome tests now
```bash
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### Case 2: I want to run all tests including API
```bash
mvn verify
```

### Case 3: I want to clean and rebuild everything
```bash
mvn clean install
```

### Case 4: I want to run tests and generate fresh reports
```bash
mvn clean test -Dtest=UITestRunner
```

### Case 5: I want to run tests with detailed output
```bash
mvn verify -X -e
```

### Case 6: I want to install browsers and run all tests
```bash
choco install firefox microsoft-edge -y
mvn verify
```

### Case 7: I want to skip tests and just build
```bash
mvn clean install -DskipTests
```

---

## 📈 Full Test Execution Examples

### Example 1: Basic Chrome Test
```bash
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```
**Expected Output**:
```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Example 2: Complete Test Suite
```bash
mvn verify
```
**Expected Output**:
```
[INFO] UI Tests: 9 scenarios
[INFO] API Tests: 4 scenarios
[INFO] Total: 13 scenarios
[INFO] Tests run: 13, Failures: 0, Errors: 0
[INFO] BUILD SUCCESS
```

### Example 3: Debug Mode Execution
```bash
mvn verify -X -e 2>&1 | tee test-output.log
```
**Output**: Saved to test-output.log with full debug info

---

## 🔍 Interpreting Maven Output

### Successful Test Run
```
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Failed Test Run
```
[ERROR] Tests run: 9, Failures: 2, Errors: 1, Skipped: 0
[ERROR] BUILD FAILURE
```

### Skipped Tests
```
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 3
```

---

## 📝 Useful Maven Options

| Option | Description | Example |
|--------|-------------|---------|
| `-X` | Debug mode | `mvn verify -X` |
| `-e` | Show errors | `mvn verify -e` |
| `-q` | Quiet mode | `mvn verify -q` |
| `-DskipTests` | Skip tests | `mvn install -DskipTests` |
| `-Dtest=` | Run specific test | `mvn test -Dtest=UITestRunner` |
| `-Dbrowser=` | Specify browser | `mvn test -Dbrowser=chrome` |
| `-Dmaven.test.skip=true` | Skip all tests | `mvn install -Dmaven.test.skip=true` |
| `-Dthreads=` | Thread count | `mvn verify -DthreadCount=4` |
| `-P` | Use profile | `mvn test -Pui-tests` |

---

## 🏗️ Maven Lifecycle Phases

| Phase | Command | Purpose |
|-------|---------|---------|
| validate | `mvn validate` | Validate project |
| compile | `mvn compile` | Compile source |
| test-compile | `mvn test-compile` | Compile tests |
| test | `mvn test` | Run unit tests |
| verify | `mvn verify` | Run all tests |
| package | `mvn package` | Create JAR/WAR |
| install | `mvn install` | Install to local repo |
| deploy | `mvn deploy` | Deploy to remote repo |

---

## 📍 Report Locations

### HTML Report
```
target/cucumber-reports/ui-report.html
```

### JSON Report
```
target/cucumber-reports/ui-report.json
```

### XML Report
```
target/cucumber-reports/ui-report.xml
```

### Test Surefire Reports
```
target/surefire-reports/
```

---

## 🎯 Quick Command Cheat Sheet

```bash
# UI Tests
mvn test -Dtest=UITestRunner                          # All browsers
mvn test -Dtest=UITestRunner -Dbrowser=chrome         # Chrome
mvn test -Dtest=UITestRunner -Dbrowser=firefox        # Firefox
mvn test -Dtest=UITestRunner -Dbrowser=edge           # Edge

# API Tests
mvn test -Dtest=TestRunner                            # All API tests

# Combined
mvn verify                                             # All tests
mvn clean verify                                       # Clean + all tests

# Build
mvn clean compile                                      # Clean & compile
mvn clean install -DskipTests                         # Install without tests

# Reports
start target\cucumber-reports\ui-report.html          # View HTML report

# Help
mvn help:active-profiles                              # Show profiles
mvn help:describe -Dplugin=plugin-name               # Plugin help
```

---

## 🚀 Recommended Commands for Different Scenarios

### Scenario 1: First Time Setup
```bash
mvn clean install
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### Scenario 2: Daily Testing
```bash
mvn verify
```

### Scenario 3: Quick Chrome Test
```bash
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### Scenario 4: Full Suite with Reports
```bash
mvn clean verify
```

### Scenario 5: Troubleshooting
```bash
mvn verify -X -e
```

### Scenario 6: CI/CD Pipeline
```bash
mvn clean verify -Dmaven.test.failure.ignore=false
```

---

## 📞 Need Help?

### Get Help on a Plugin
```bash
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-surefire-plugin
```

### List All Goals
```bash
mvn help:goals
```

### Describe a Goal
```bash
mvn help:describe -Dgoal=test
```

---

## ✅ Command Validation Checklist

Before running any Maven command:
- ✅ Java installed: `java -version`
- ✅ Maven installed: `mvn -version`
- ✅ In correct directory: `cd C:\Users\hp\IdeaProjects\TestAssignment`
- ✅ pom.xml exists: `dir pom.xml`
- ✅ Source code present: `dir src`

---

## 🔗 Related Documentation

- `QUICK_HEADLESS_REFERENCE.md` - Quick start guide
- `BROWSER_SETUP_GUIDE.md` - Browser installation
- `HEADLESS_UI_TESTING_REPORT.md` - Test results
- `run-tests.bat` - Interactive test menu (Windows)
- `run-tests.sh` - Interactive test menu (Linux/Mac)

---

**For interactive test execution, use**: `run-tests.bat` (Windows) or `run-tests.sh` (Linux/Mac)

---

*Maven Command Reference v1.0 | May 18, 2026*

