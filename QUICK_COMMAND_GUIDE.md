# Quick Command Guide - Run UI & API Tests

**Start Date**: May 18, 2026  
**Quick Reference**: Maven Commands for Test Execution  

---

##  FASTEST WAY TO RUN TESTS

### 1. Run Chrome UI Tests (Fastest - 30 seconds)
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### 2. Run All UI Tests (2-3 minutes)
```powershell
mvn test -Dtest=UITestRunner
```

### 3. Run API Tests (1 minute)
```powershell
mvn test -Dtest=TestRunner
```

### 4. Run EVERYTHING (UI + API, 3 minutes)
```powershell
mvn verify
```

---

## COPY & PASTE COMMANDS

### UI Tests - Copy These Exact Commands

```powershell
# Chrome UI Tests (Headless)
mvn test -Dtest=UITestRunner -Dbrowser=chrome

# Firefox UI Tests (Headless)
mvn test -Dtest=UITestRunner -Dbrowser=firefox

# Edge UI Tests (Headless)
mvn test -Dtest=UITestRunner -Dbrowser=edge

# All UI Tests (Chrome + Firefox + Edge)
mvn test -Dtest=UITestRunner
```

### API Tests - Copy These Exact Commands

```powershell
# Run All API Tests
mvn test -Dtest=TestRunner
```

### Combined Tests

```powershell
# Run ALL Tests (UI + API)
mvn verify

# Clean Build + All Tests
mvn clean verify

# All Tests with Debug Output
mvn verify -X -e
```

---

##  QUICK START OPTIONS

### Option 1: Use Interactive Menu (Windows)
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
run-tests.bat
```

### Option 2: Use Interactive Menu (Linux/Mac)
```bash
cd C:\Users\hp\IdeaProjects\TestAssignment
bash run-tests.sh
```

### Option 3: Run Direct Commands
See "COPY & PASTE COMMANDS" section above

---

##  EXPECTED RESULTS

### Chrome Test Results
```
✅ Login to SauceDemo on Chrome - PASSED
✅ Add to Cart and Checkout - PASSED
✅ Verify Inventory Items - PASSED

Total: 3/3 PASSED
Time: ~30-45 seconds
```

### API Test Results
```
 Get list of users - Passed (200 Ok)
 Create a new user - Passed (201 Created)
 Update existing user - Passed (200 OK)
 Delete a user - - Passed (200 OK)

Total: 4/4 Passed 
Time: ~10 seconds
```

### All Tests Combined
```
UI Tests: 9 scenarios (Chrome, Firefox, Edge)
API Tests: 4 scenarios

Total: 13 scenarios
Expected: Most UI pass, API should pass
```

---

## 🔧 COMMAND BREAKDOWN

### Command Structure
```
mvn [PHASE] -Dtest=[TEST_CLASS] -Dbrowser=[BROWSER]
  │       │              │                    │
  │       └─ test/verify │                    └─ chrome/firefox/edge
  │                      └─ UITestRunner/TestRunner
  └─ Maven executable
```

### Examples
```
mvn test -Dtest=UITestRunner -Dbrowser=chrome
├─ mvn test           → Run tests phase
├─ UITestRunner       → Test class to run
└─ chrome             → Use Chrome browser

mvn verify
├─ mvn verify         → Run full test cycle
└─ All tests & all browsers
```

---

## 📁 WHERE TO RUN THESE COMMANDS

### Location
```
cd C:\Users\hp\IdeaProjects\TestAssignment
```

### Verify You're in the Right Place
```powershell
# Should exist and show content
dir pom.xml
dir src/test

# Should output project info
mvn -v
```

---

## 📈 PRACTICAL EXAMPLES

### Example 1: I want to test Chrome quickly
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```
**Time**: 1 minute  
**Result**: 3 tests  
**Output**: HTML report in target/cucumber-reports/ui-report.html

### Example 2: I want to test everything
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn verify
```
**Time**: 2-3 minutes  
**Result**: 13 tests (9 UI + 4 API)  
**Output**: HTML, JSON, XML reports

### Example 3: I want to check if Firefox works
```powershell
# First, install Firefox
choco install firefox -y

# Then run tests
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=firefox
```
**Time**: 2 minutes  
**Result**: 3 Firefox tests

### Example 4: I want to clean everything and test
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn clean verify
```
**Time**: 3-4 minutes  
**Result**: Complete clean build + all tests  
**Output**: Fresh reports

---

## 🎬 STEP-BY-STEP EXECUTION

### Step 1: Open Command Prompt
```
Windows Key + R
Type: cmd
Press Enter
```

### Step 2: Navigate to Project
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
```

### Step 3: Run a Test Command
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### Step 4: Wait for Results
```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Step 5: View Results
```powershell
# Open HTML report
start target\cucumber-reports\ui-report.html
```

---

## ⚠️ COMMON ISSUES & SOLUTIONS

### Issue: "mvn is not recognized"
**Solution**:
```
Maven not installed or not in PATH
Install Maven from: https://maven.apache.org/download.cgi
Add to PATH: C:\path\to\maven\bin
```

### Issue: "Could not find a source Java class file"
**Solution**:
```
Java not installed or not in PATH
Install Java from: https://www.oracle.com/java/
Verify: java -version
```

### Issue: "Firefox not found"
**Solution**:
```
Browser not installed
Install: choco install firefox -y
Or: Download from https://www.mozilla.org/firefox/
```

### Issue: Timeout Error
**Solution**:
```
Tests taking too long
Use fewer threads: -DthreadCount=1
Or increase timeout in pom.xml
```

---

## 🔍 CHECKING TEST RESULTS

### CLI Output
```powershell
# Check at end of test run
[INFO] Tests run: X, Failures: Y, Errors: Z, Skipped: W
[INFO] BUILD SUCCESS (or FAILURE)
```

### HTML Report (Best for viewing)
```powershell
start target\cucumber-reports\ui-report.html
```

### View Detailed Test Output
```powershell
mvn test -Dtest=UITestRunner -X  # Add -X for verbose
```

---

## COMPLETE COMMAND REFERENCE

| Purpose | Command |
|---------|---------|
| Chrome UI Tests | `mvn test -Dtest=UITestRunner -Dbrowser=chrome` |
| Firefox UI Tests | `mvn test -Dtest=UITestRunner -Dbrowser=firefox` |
| Edge UI Tests | `mvn test -Dtest=UITestRunner -Dbrowser=edge` |
| All UI Tests | `mvn test -Dtest=UITestRunner` |
| API Tests | `mvn test -Dtest=TestRunner` |
| All Tests | `mvn verify` |
| Clean + All | `mvn clean verify` |
| With Debug | `mvn verify -X -e` |
| Skip Tests | `mvn install -DskipTests` |
| View Report | `start target\cucumber-reports\ui-report.html` |

---


###  option 1: Create a Batch File
Create `quick-test.bat` in project folder:
```batch
@echo off
mvn test -Dtest=UITestRunner -Dbrowser=chrome
pause
```
Then just double-click to run!

### option 2: Use PowerShell Alias
```powershell
Set-Alias mtest 'mvn test -Dtest=UITestRunner -Dbrowser=chrome'
mtest  # Now runs the command
```

### option 3: Save Output to File
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome > test-results.txt
```

### option 4: Run Multiple Commands
```powershell
mvn clean verify; start target\cucumber-reports\ui-report.html
```

---


### Chrome Tests Passing
```
✅ 3/3 tests passed
✅ Build SUCCESS
✅ HTML report generated
```

### All Tests Passing
```
✅ UI tests: 9/9 passed
✅ API tests: May have 401 errors (expected)
✅ Build SUCCESS
✅ Complete HTML report
```

---

##  QUICK HELP

### For Detailed Commands
See: `MAVEN_COMMAND_REFERENCE.md`

### For Browser Setup
See: `BROWSER_SETUP_GUIDE.md`

### For Headless Mode
See: `QUICK_HEADLESS_REFERENCE.md`

### For Test Details
See: `HEADLESS_UI_TESTING_REPORT.md`

---

## 🚀 START NOW

### Option 1: Chrome Test (Fastest - Now!)
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### Option 2: Interactive Menu
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
run-tests.bat
```

### Option 3: All Tests
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn verify
```

---

**TL;DR**:
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

That's it! Tests will run. 🎉

---

*Quick Command Guide v1.0 | May 18, 2026*

