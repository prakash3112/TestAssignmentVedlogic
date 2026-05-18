# 🎯 UI & API Tests - Maven Command Line Guide

**Date**: May 18, 2026  
**Status**: ✅ Ready for Use  
**Framework**: Selenium + Cucumber + Maven  

---

## 📖 Documentation Overview

This project includes comprehensive guides for running UI and API tests from the command line:

### Documents Provided
1. **QUICK_COMMAND_GUIDE.md** ← **START HERE** (Copy & Paste Commands)
2. **MAVEN_COMMAND_REFERENCE.md** (Detailed Maven Reference)
3. **run-tests.bat** (Interactive Windows Menu)
4. **run-tests.sh** (Interactive Linux/Mac Menu)
5. **QUICK_HEADLESS_REFERENCE.md** (Headless Mode Guide)
6. **BROWSER_SETUP_GUIDE.md** (Browser Installation)

---

## 🚀 FASTEST WAY TO GET STARTED

### Step 1: Open Command Prompt
```
Windows Key + R → type: cmd → Enter
```

### Step 2: Navigate to Project
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
```

### Step 3: Run A Test
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

**That's it!** Tests will run and complete in ~1 minute. ✅

---

## 📋 MOST COMMON COMMANDS

### Run UI Tests
```bash
# Chrome (Fastest - Recommended)
mvn test -Dtest=UITestRunner -Dbrowser=chrome

# All Browsers
mvn test -Dtest=UITestRunner

# Firefox
mvn test -Dtest=UITestRunner -Dbrowser=firefox

# Edge
mvn test -Dtest=UITestRunner -Dbrowser=edge
```

### Run API Tests
```bash
# All API tests
mvn test -Dtest=TestRunner
```

### Run Everything
```bash
# ALL tests (UI + API)
mvn verify

# Clean + ALL tests
mvn clean verify
```

---

## 🎯 CHOOSE YOUR INTERACTION METHOD

### Method 1: Interactive Menu (Easiest for Windows)
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
run-tests.bat
```
✅ **User-Friendly Menu**  
✅ **No Commands to Remember**  
✅ **Visual Feedback**  

### Method 2: Copy & Paste Commands (Fastest)
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```
✅ **Direct Execution**  
✅ **Full Control**  
✅ **CLI Experience**  

### Method 3: Read Reference Guide (Comprehensive)
Open: `QUICK_COMMAND_GUIDE.md`  
✅ **All Commands Listed**  
✅ **Examples Provided**  
✅ **Troubleshooting Tips**  

---

## 🔧 COMMAND TEMPLATES

### Template 1: UI Tests
```
mvn test -Dtest=UITestRunner [-Dbrowser=chrome|firefox|edge]
```

**Examples**:
```
mvn test -Dtest=UITestRunner                           # All browsers
mvn test -Dtest=UITestRunner -Dbrowser=chrome          # Chrome only
mvn test -Dtest=UITestRunner -Dbrowser=firefox         # Firefox only
mvn test -Dtest=UITestRunner -Dbrowser=edge            # Edge only
```

### Template 2: API Tests
```
mvn test -Dtest=TestRunner
```

### Template 3: Combined
```
mvn verify
```

### Template 4: With Options
```
mvn verify [-X] [-e] [-DskipTests] [-DthreadCount=N]
```

**Options**:
- `-X` = Debug mode
- `-e` = Show errors
- `-DskipTests` = Skip tests
- `-DthreadCount=4` = 4 parallel threads

---

## 📊 WHAT EACH COMMAND DOES

| Command | Runs | Time | Output |
|---------|------|------|--------|
| `mvn test -Dtest=UITestRunner -Dbrowser=chrome` | 3 UI tests (Chrome) | ~45s | HTML report |
| `mvn test -Dtest=UITestRunner` | 9 UI tests (All browsers) | ~2m | HTML report |
| `mvn test -Dtest=TestRunner` | 4 API tests | ~10s | Console output |
| `mvn verify` | 13 tests (UI + API) | ~3m | HTML/JSON/XML |
| `mvn clean verify` | Clean + all tests | ~4m | Full reports |

---

## ✨ REAL-WORLD EXAMPLES

### Example 1: I'm a Developer - Quick Test Before Commit
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```
**Time**: 1 minute  
**Purpose**: Quick validation  
**Output**: HTML report

### Example 2: I'm QA - Full Test Suite
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn verify
```
**Time**: 3 minutes  
**Purpose**: Complete validation  
**Output**: Complete test reports

### Example 3: I'm DevOps - CI/CD Pipeline
```powershell
cd project-folder
mvn clean verify -Dmaven.test.failure.ignore=false
```
**Time**: 4 minutes  
**Purpose**: Automated testing  
**Output**: Reports for artifacts

### Example 4: I'm Debugging - Verbose Output
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn verify -X -e
```
**Time**: 4 minutes  
**Purpose**: Troubleshooting  
**Output**: Full debug logs

---

## 🎬 STEP-BY-STEP WALKTHROUGH

### STEP 1: Open Command Prompt
```
Press Windows Key
Type: cmd
Press Enter
```

### STEP 2: Navigate to Project
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
```

### STEP 3: Verify You're in Right Place
```powershell
dir pom.xml  # Should show pom.xml file
```

### STEP 4: Run a Test
```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### STEP 5: Wait for Results
```
BUILD SUCCESS appears at end
HTML report generated
```

### STEP 6: View Results (Optional)
```powershell
start target\cucumber-reports\ui-report.html
```

---

## 📈 UNDERSTANDING OUTPUT

### Successful Run
```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```
✅ All tests passed!

### Failed Run
```
[ERROR] Tests run: 9, Failures: 2, Errors: 1, Skipped: 0
[ERROR] BUILD FAILURE
```
❌ Some tests failed. Check HTML report.

### With Errors
```
[ERROR] Some error message
[ERROR] BUILD FAILURE
```
❌ Error during execution. Read the error message.

---

## 🔍 FINDING TEST RESULTS

### Option 1: HTML Report (Best for viewing)
```powershell
# Location
target/cucumber-reports/ui-report.html

# Open it
start target\cucumber-reports\ui-report.html
```

### Option 2: JSON Report (For tools)
```
target/cucumber-reports/ui-report.json
```

### Option 3: XML Report (For CI/CD)
```
target/cucumber-reports/ui-report.xml
```

### Option 4: Console Output
```
Last lines of Maven output show summary
```

---

## 🛠️ FIRST-TIME SETUP

### Requirement 1: Java
```powershell
# Check if installed
java -version

# If not installed
# Download from: https://www.oracle.com/java/
```

### Requirement 2: Maven
```powershell
# Check if installed
mvn -version

# If not installed
# Download from: https://maven.apache.org/
```

### Requirement 3: Chrome (Automatic)
```powershell
# Chrome driver is downloaded automatically
# No manual setup needed!
```

### Requirement 4: Firefox (Optional but Recommended)
```powershell
# For Firefox tests
choco install firefox -y
```

### Requirement 5: Edge (Optional)
```powershell
# For Edge tests
choco install microsoft-edge -y
```

---

## 💡 TIPS & TRICKS

### Tip 1: Create a Shortcut Batch File
Create file `test-chrome.bat`:
```batch
@echo off
mvn test -Dtest=UITestRunner -Dbrowser=chrome
pause
```
Double-click to run!

### Tip 2: Save Test Output
```powershell
mvn verify > results.txt
```
Now check `results.txt` file.

### Tip 3: Run Multiple Commands
```powershell
mvn clean verify; start target\cucumber-reports\ui-report.html
```

### Tip 4: Use PowerShell Alias
```powershell
Set-Alias test-chrome "mvn test -Dtest=UITestRunner -Dbrowser=chrome"
test-chrome  # Now just type this
```

### Tip 5: Run Tests Faster
```powershell
# Use quiet mode
mvn test -Dtest=UITestRunner -Dbrowser=chrome -q

# Use offline mode
mvn test -Dtest=UITestRunner -Dbrowser=chrome -o
```

---

## 🆘 COMMON ISSUES

### Issue 1: "mvn" command not found
```
Solution: Add Maven to PATH
1. Download Maven from maven.apache.org
2. Extract to C:\Maven
3. Add C:\Maven\bin to System PATH
4. Restart command prompt
```

### Issue 2: "Firefox not found" error
```
Solution: Install Firefox
choco install firefox -y

Or manually download from mozilla.org
```

### Issue 3: Tests timeout
```
Solution: Increase timeout
Edit pom.xml and change forkedProcessTimeoutInSeconds to 600
```

### Issue 4: Port already in use
```
Solution: Kill browser processes
taskkill /F /IM chromedriver.exe
taskkill /F /IM geckodriver.exe
taskkill /F /IM msedgedriver.exe
```

---

## 📚 COMPLETE RESOURCE LIST

### Quick Reference
- **QUICK_COMMAND_GUIDE.md** - Copy & paste commands
- **QUICK_HEADLESS_REFERENCE.md** - Headless mode guide

### Detailed Information
- **MAVEN_COMMAND_REFERENCE.md** - All Maven commands
- **BROWSER_SETUP_GUIDE.md** - Browser installation
- **HEADLESS_UI_TESTING_REPORT.md** - Test results analysis

### Interactive Tools
- **run-tests.bat** - Windows menu script
- **run-tests.sh** - Linux/Mac menu script

### Test Scenarios
- **UI-Test.feature** - BDD scenarios (src/test/resources/features/)
- **API-Test.feature** - API test scenarios

---

## ✅ BEFORE YOU START

Verify these exist:
- ✅ Java installed: `java -version`
- ✅ Maven installed: `mvn -version`
- ✅ In correct folder: `cd C:\Users\hp\IdeaProjects\TestAssignment`
- ✅ pom.xml exists: `dir pom.xml`

---

## 🎯 YOUR WORKFLOW

```
1. Open Command Prompt
   ↓
2. Navigate: cd C:\Users\hp\IdeaProjects\TestAssignment
   ↓
3. Choose one:
   - mvn test -Dtest=UITestRunner -Dbrowser=chrome  (Quick)
   - run-tests.bat                                   (Interactive)
   - mvn verify                                      (Complete)
   ↓
4. Wait for completion
   ↓
5. I want to see HTML report?
   start target\cucumber-reports\ui-report.html
   ↓
6. Done! ✅
```

---

## 🚀 GET STARTED NOW

### Option 1: Interactive Menu (Recommended)
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
run-tests.bat
```

### Option 2: Chrome Test (Fastest)
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome
```

### Option 3: Everything
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn verify
```

**Pick one and run it now!** 🎉

---

## 📞 NEED HELP?

| Question | Answer |
|----------|--------|
| **What's the fastest test?** | `mvn test -Dtest=UITestRunner -Dbrowser=chrome` |
| **How do I run all tests?** | `mvn verify` |
| **Where are report files?** | `target/cucumber-reports/` |
| **How do I install browsers?** | `choco install firefox microsoft-edge -y` |
| **I want a menu, not commands** | Run `run-tests.bat` |
| **I want detailed output** | Add `-X -e` to any command |

---

## ✨ QUICK SUMMARY

**3 Easy Ways to Run Tests:**

1. **Interactive Menu**
   ```powershell
   run-tests.bat
   ```

2. **Chrome Test**
   ```powershell
   mvn test -Dtest=UITestRunner -Dbrowser=chrome
   ```

3. **Everything**
   ```powershell
   mvn verify
   ```

Pick one, run it, done! ✅

---

*UI & API Maven Guide v1.0 | May 18, 2026*  
**Start with QUICK_COMMAND_GUIDE.md for copy-paste commands!**

