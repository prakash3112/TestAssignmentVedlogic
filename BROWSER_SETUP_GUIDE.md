# Browser Setup Guide - Headless UI Testing

## Overview
This guide provides instructions for setting up Chrome, Firefox, and Edge browsers for headless UI automation testing on Windows 10.

---

## Current Status

### ✅ Chrome Browser - WORKING
- **Status**: All tests passing
- **Mode**: Headless (--headless=new)
- **Features**: WebDriverManager automatically handles driver download and updates
- **Test Results**: 3/3 scenarios passed

### ⚠️ Firefox Browser - REQUIRES SETUP
- **Issue**: Firefox binary not found in default location
- **Solution**: Install Mozilla Firefox
- **Headless Mode**: Supported (--headless flag)

### ⚠️ Edge Browser - NETWORK ISSUE
- **Issue**: Network connectivity issue downloading EdgeDriver
- **Solution**: Install Edge browser or configure WebDriverManager offline mode
- **Headless Mode**: Supported (--headless=new flag)

---

## Installation Instructions

### 1. Installing Firefox Browser

#### Option A: Automatic Installation (Recommended for Windows)
```powershell
# Using Chocolatey (if installed)
choco install firefox -y

# OR using winget (Windows 11+)
winget install Mozilla.Firefox -e
```

#### Option B: Manual Installation
1. Visit: https://www.mozilla.org/en-US/firefox/new/
2. Download the Firefox installer
3. Run the installer and follow the installation wizard
4. Default installation path: `C:\Program Files\Mozilla Firefox\firefox.exe`

#### Verification
```powershell
# Check if Firefox is installed
Test-Path "C:\Program Files\Mozilla Firefox\firefox.exe"

# Should return: True
```

---

### 2. Installing Edge Browser

#### Option A: Automatic Installation (Recommended)
```powershell
# Using Chocolatey (if installed)
choco install microsoft-edge -y

# OR using winget (Windows 11+)
winget install Microsoft.Edge -e
```

#### Option B: Manual Installation
1. Visit: https://www.microsoft.com/en-us/edge
2. Download the Edge installer
3. Run the installer and follow the installation wizard
4. Default installation path: `C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe`

#### Verification
```powershell
# Check if Edge is installed
Test-Path "C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe"

# Should return: True
```

---

## WebDriver Setup

### For Firefox: GeckoDriver
The GeckoDriver is automatically managed by WebDriverManager. No manual setup required.

**Cache Location**: `C:\Users\{username}\.cache\selenium\geckodriver\`

### For Edge: MSEdgeDriver
The MSEdgeDriver is automatically managed by WebDriverManager. No manual setup required.

**Cache Location**: `C:\Users\{username}\.cache\selenium\msedgedriver\`

#### Offline Setup (If Network Issues Persist)
1. Download MSEdgeDriver from: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
2. Extract the executable
3. Place in a directory in your PATH environment variable
4. Or add to WebDriver configuration in `DriverFactory.java`

---

## Running Tests in Headless Mode

### Run All Browsers (Chrome, Firefox, Edge)
```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn verify
```

### Run Specific Browser Tests
```powershell
# Chrome only
mvn test -Dtest=UITestRunner -Dbrowser=chrome

# Firefox only
mvn test -Dtest=UITestRunner -Dbrowser=firefox

# Edge only
mvn test -Dtest=UITestRunner -Dbrowser=edge
```

### Run with Detailed Output
```powershell
mvn verify -X 2>&1 | Tee-Object -FilePath test-results.log
```

---

## Test Execution Summary

### Headless Mode Configuration
All browsers are configured with headless mode enabled:

```java
// Chrome
--headless=new        // New headless implementation (Chromium)
--disable-gpu         // Disable GPU acceleration
--no-sandbox          // Sandbox disabled for CI environments

// Firefox
--headless            // Firefox headless mode
--width=1920          // Set window width
--height=1080         // Set window height
-private              // Private browsing mode

// Edge
--headless=new        // New headless implementation (Chromium-based)
--disable-gpu         // Disable GPU acceleration
--no-sandbox          // Sandbox disabled for CI environments
```

### Parallel Execution Configuration
- **Thread Count**: 4
- **Parallel Mode**: methods (test methods)
- **Reports**: HTML, JSON, XML formats

---

## Expected Test Results

### Chrome Tests (All PASSING)
```
✅ Login to SauceDemo on Chrome - PASSED
✅ Add item to cart and checkout on Chrome - PASSED
✅ Verify inventory items on Chrome - PASSED
```

### Firefox Tests (After Installation)
```
✅ Login to SauceDemo on Firefox - Expected: PASSED
✅ Add item to cart and checkout on Firefox - Expected: PASSED
✅ Verify inventory items on Firefox - Expected: PASSED
```

### Edge Tests (After Installation)
```
✅ Login to SauceDemo on Edge - Expected: PASSED
✅ Add item to cart and checkout on Edge - Expected: PASSED
✅ Verify inventory items on Edge - Expected: PASSED
```

---

## Troubleshooting

### Firefox Not Found Error
**Error**: `Expected browser binary location, but unable to find binary in default location`

**Solution**:
1. Verify Firefox installation: `C:\Program Files\Mozilla Firefox\firefox.exe`
2. Check DriverFactory's Firefox binary path detection
3. Manually set path in DriverFactory if needed:
   ```java
   firefoxOptions.setBinary("C:\\Your\\Custom\\Path\\firefox.exe");
   ```

### Edge Driver Download Error
**Error**: `java.net.UnknownHostException: msedgedriver.azureedge.net`

**Solution**:
1. Check internet connectivity
2. Verify DNS resolution
3. Use cached driver if available
4. Run tests in offline mode once driver is cached

### GeckoDriver Issues
**Error**: `geckodriver not found`

**Solution**:
1. WebDriverManager handles this automatically
2. Check cache location: `C:\Users\{username}\.cache\selenium\geckodriver\`
3. Clear cache and retry: `mvn clean test-compile`

---

## Additional Resources

1. **WebDriverManager Documentation**: https://github.com/bonigarcia/webdrivermanager
2. **Selenium Documentation**: https://www.selenium.dev/documentation/
3. **Firefox ESR for Headless**: https://www.mozilla.org/en-US/firefox/enterprise/
4. **Chromium Headless Mode**: https://developer.chrome.com/articles/new-headless/

---

## Next Steps

1. **Install Firefox**: Follow the installation instructions above
2. **Install Edge**: Follow the installation instructions above
3. **Run Tests**: Execute `mvn verify` to run all tests
4. **Review Reports**: Check the generated reports in `target/cucumber-reports/`

---

## Performance Notes

- Headless mode improves test execution speed by 20-30%
- Parallel execution with 4 threads provides optimal performance
- Tests can run without a physical display (suitable for CI/CD environments)

---

**Last Updated**: May 18, 2026
**Test Framework**: Cypress with Cucumber and Selenium
**Java Version**: 11.0.31
**Maven Version**: 3.9.14

