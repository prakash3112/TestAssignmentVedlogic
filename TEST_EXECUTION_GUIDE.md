# Complete Test Execution Guide - Parallel & Cross-Browser

## Quick Start Guide

### Prerequisites
- Java 8 or higher
- Maven 3.6 or higher
- 4+ CPU cores
- 8+ GB RAM (16 GB recommended)
- Browsers: Chrome, Firefox, Edge (with drivers)

---

## Execution Methods

### Method 1: Maven Command Line (Recommended)

#### Run All Tests
```bash
mvn clean test
```

#### Run UI Tests Only (Parallel - 4 Threads)
```bash
mvn clean test -Dtest=UITestRunner
```

#### Run API Tests Only
```bash
mvn clean test -Dtest=TestRunner
```

#### Run Chrome Tests Only
```bash
mvn clean test -Dcucumber.filter.tags="@UI and @Chrome"
```

#### Run Firefox Tests Only
```bash
mvn clean test -Dcucumber.filter.tags="@UI and @Firefox"
```

#### Run Edge Tests Only
```bash
mvn clean test -Dcucumber.filter.tags="@UI and @Edge"
```

#### Run with Custom Browser
```bash
mvn clean test -Dbrowser=firefox -Dtest=UITestRunner
```

#### Run with Custom Thread Count
```bash
mvn clean test -DthreadCount=8 -Dtest=UITestRunner
```

---

### Method 2: Windows Batch Script

#### Simple Execution
```batch
run-ui-parallel-tests.bat
```

**Features**:
- Automatically runs 4 parallel threads
- Executes @UI tagged scenarios
- Generates reports in target directory
- Pauses at end to view results

---

### Method 3: Unix/Linux Shell Script

#### Simple Execution
```bash
./run-ui-parallel-tests.sh
```

---

### Method 4: IDE Integration (IntelliJ IDEA)

#### Run Specific Test Runner
1. Right-click on `UITestRunner.java`
2. Select "Run 'UITestRunner'"

#### Run Specific Feature
1. Right-click on `UI-Test.feature`
2. Select "Run all Scenarios in UI-Test"

#### Run Specific Scenario
1. Right-click on scenario line
2. Select "Run" or "Run with Options"

---

## Test Execution Flows

### Flow 1: Sequential Test Execution
```
Maven Build
    ↓
Load TestRunner (API)
    ↓
Execute @API Scenarios (Sequential)
    ↓
Load UITestRunner (UI)
    ↓
Execute @UI Scenarios (Parallel - 4 threads)
    ↓
Generate Reports
    ↓
Test Complete
```

### Flow 2: Parallel Test Execution (4 Threads)
```
Maven Build
    ↓
Thread 1 (Chrome)    Thread 2 (Firefox)    Thread 3 (Edge)    Thread 4 (Chrome)
    ↓                    ↓                      ↓                  ↓
Scenario 1          Scenario 2             Scenario 3         Scenario 4
Scenario 5          Scenario 6             Scenario 7         Scenario 8
Scenario 9
    ↓                    ↓                      ↓                  ↓
    └────────────────┬───────────────────┬────────────────┘
                     ↓
              Generate Reports
                     ↓
              Test Complete
```

---

## Test Scenarios Breakdown

### API Tests (4 Scenarios - Sequential)
1. Get list of users (GET)
2. Create a new user (POST)
3. Update an existing user (PUT)
4. Delete a user (DELETE)

### UI Tests (9 Scenarios - Parallel)
**Chrome Tests**:
1. Login to SauceDemo on Chrome
2. Add item to cart and checkout on Chrome
3. Verify inventory items on Chrome

**Firefox Tests**:
4. Login to SauceDemo on Firefox
5. Add item to cart and checkout on Firefox
6. Verify inventory items on Firefox

**Edge Tests**:
7. Login to SauceDemo on Edge
8. Add item to cart and checkout on Edge
9. Verify inventory items on Edge

---

## Thread Group Mapping

### Default Mapping (4 Threads)
```
Thread 1 → Chrome
Thread 2 → Firefox
Thread 3 → Edge
Thread 4 → Chrome
```

### Custom Mapping
You can override browser selection using:
- Feature file tags (@Chrome, @Firefox, @Edge)
- System property: `-Dbrowser=firefox`
- ParallelExecutionConfig class methods

---

## Report Generation

### Report Locations
```
target/
├── cucumber-reports/
│   ├── api-report.html          (API test report)
│   ├── api-report.json          (API test data)
│   ├── ui-report.html           (UI test report)
│   ├── ui-report.json           (UI test data)
│   └── ui-report.xml            (UI test XML)
└── surefire-reports/            (Maven Surefire reports)
```

### Opening Reports
1. **HTML Reports**:
   - Double-click `.html` file to open in browser
   - View detailed test results
   - See screenshots and logs

2. **JSON Reports**:
   - Use with CI/CD tools
   - Parse for metrics and analysis

3. **XML Reports**:
   - Integration with JUnit reporting
   - CI/CD pipeline integration

---

## Configuration Options

### System Properties
```bash
# Browser selection
-Dbrowser=chrome|firefox|edge

# Thread configuration
-DthreadCount=4
-DforkCount=1

# Test filtering
-Dcucumber.filter.tags="@UI and @Chrome"

# Parallel mode
-Dparallel=methods|classes|suitesThreads
```

### Environment Variables
```bash
# Set JVM options
set MAVEN_OPTS=-Xmx2048m -Xms1024m

# Set timeout
set FORKED_PROCESS_TIMEOUT=300
```

---

## Performance Metrics

### Execution Time Estimates

| Configuration | Time |
|---------------|------|
| Sequential (API + UI) | ~270 seconds (~4.5 min) |
| Parallel (4 threads) | ~70 seconds (~1.2 min) |
| API Only | ~60 seconds (~1 min) |
| UI Chrome Only | ~90 seconds (~1.5 min) |
| UI All Browsers | ~100 seconds (~1.7 min) |

### Performance Tips
1. Use SSD for faster I/O
2. Allocate sufficient RAM
3. Use `reuseForks=true`
4. Minimize browser operations
5. Use headless mode if needed

---

## Troubleshooting Guide

### Issue: WebDriver Not Found
**Solution**:
```bash
# Ensure WebDriverManager is configured
# Check Maven dependencies in pom.xml
mvn dependency:resolve
```

### Issue: Port Already in Bind
**Solution**:
```bash
# Kill existing processes
# Windows:
taskkill /F /IM chromedriver.exe
taskkill /F /IM geckodriver.exe

# Unix/Linux:
pkill -f chromedriver
pkill -f geckodriver
```

### Issue: Thread Timeout
**Solution**:
```xml
<!-- In pom.xml, increase timeout -->
<forkedProcessTimeoutInSeconds>600</forkedProcessTimeoutInSeconds>
```

### Issue: Out of Memory
**Solution**:
```bash
# Increase JVM heap
set MAVEN_OPTS=-Xmx4096m -Xms2048m
mvn clean test
```

### Issue: Tests Run Sequentially Instead of Parallel
**Solution**:
```bash
# Verify configuration
mvn test -DdescribeTestPhase

# Check pom.xml has correct settings:
# <parallel>methods</parallel>
# <threadCount>4</threadCount>
```

---

## Continuous Integration Integration

### Jenkins Pipeline Example
```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
    post {
        always {
            cucumber 'target/cucumber-reports/**/*.json'
            junit 'target/surefire-reports/**/*.xml'
        }
    }
}
```

### GitLab CI Example
```yaml
test:
  script:
    - mvn clean test
  artifacts:
    reports:
      junit: target/surefire-reports/**/*.xml
    paths:
      - target/cucumber-reports/
```

---

## Best Practices

### Do's ✅
- Use thread-safe operations
- Clean up resources in hooks
- Use meaningful test names
- Implement proper waits
- Log important information

### Don'ts ❌
- Don't share WebDriver instances
- Don't use sleep() instead of waits
- Don't ignore exceptions
- Don't skip cleanup
- Don't hardcode credentials

---

## Parallel Execution Benefits

1. **Speed**: 3-4x faster test execution
2. **Efficiency**: Better resource utilization
3. **Scalability**: Easy to add more threads
4. **Coverage**: Run more tests in less time
5. **CI/CD**: Faster feedback in pipelines

---

## Command Reference

### Clean Build
```bash
mvn clean
```

### Compile Only
```bash
mvn compile
```

### Test Compile Only
```bash
mvn test-compile
```

### Run Tests
```bash
mvn test
```

### Run Specific Test Runner
```bash
mvn test -Dtest=UITestRunner
mvn test -Dtest=TestRunner
```

### Run with Tags
```bash
mvn test -Dcucumber.filter.tags="@UI"
mvn test -Dcucumber.filter.tags="@API"
mvn test -Dcucumber.filter.tags="@Chrome"
```

### Run with Browser Parameter
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
mvn test -Dbrowser=chrome
```

### Generate Reports
```bash
mvn site
```

---

## Summary

This framework provides:
- ✅ Parallel execution (4 threads)
- ✅ Cross-browser testing (Chrome, Firefox, Edge)
- ✅ Thread-safe WebDriver management
- ✅ Comprehensive reporting
- ✅ Flexible configuration
- ✅ CI/CD ready
- ✅ Performance optimized
- ✅ Well documented

**Get started**: `mvn clean test -Dtest=UITestRunner`

---

Generated: May 18, 2026

