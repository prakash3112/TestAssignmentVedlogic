@echo off
REM Script to install Firefox and run headless UI tests
REM Compatible with Windows 10+

setlocal enabledelayedexpansion

echo.
echo ============================================================
echo     Headless Browser UI Testing - Setup & Execution
echo ============================================================
echo.

REM Check if Firefox is installed
echo Checking for Firefox installation...
if exist "C:\Program Files\Mozilla Firefox\firefox.exe" (
    echo [OK] Firefox found at: C:\Program Files\Mozilla Firefox\firefox.exe
    set FIREFOX_INSTALLED=1
) else if exist "C:\Program Files (x86)\Mozilla Firefox\firefox.exe" (
    echo [OK] Firefox found at: C:\Program Files (x86)\Mozilla Firefox\firefox.exe
    set FIREFOX_INSTALLED=1
) else (
    echo [WARNING] Firefox not found in default locations
    echo Please install Firefox from: https://www.mozilla.org/en-US/firefox/new/
    set FIREFOX_INSTALLED=0
)

echo.

REM Check if Edge is installed
echo Checking for Microsoft Edge installation...
if exist "C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe" (
    echo [OK] Microsoft Edge found
    set EDGE_INSTALLED=1
) else (
    echo [WARNING] Microsoft Edge not found
    echo Please install Edge from: https://www.microsoft.com/en-us/edge
    set EDGE_INSTALLED=0
)

echo.

REM Check if Chrome is installed
echo Checking for Google Chrome installation...
if exist "C:\Program Files\Google\Chrome\Application\chrome.exe" (
    echo [OK] Google Chrome found
    set CHROME_INSTALLED=1
) else if exist "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" (
    echo [OK] Google Chrome found
    set CHROME_INSTALLED=1
) else (
    echo [WARNING] Google Chrome not found
    set CHROME_INSTALLED=0
)

echo.
echo ============================================================
echo             Browser Installation Summary
echo ============================================================
if !CHROME_INSTALLED! equ 1 (
    echo [✓] Chrome: INSTALLED
) else (
    echo [✗] Chrome: NOT INSTALLED
)

if !FIREFOX_INSTALLED! equ 1 (
    echo [✓] Firefox: INSTALLED
) else (
    echo [✗] Firefox: NOT INSTALLED
)

if !EDGE_INSTALLED! equ 1 (
    echo [✓] Edge: INSTALLED
) else (
    echo [✗] Edge: NOT INSTALLED
)

echo.
echo Browsers ready for headless testing: !CHROME_INSTALLED! !FIREFOX_INSTALLED! !EDGE_INSTALLED!
echo.

REM Change to project directory
cd /d "C:\Users\hp\IdeaProjects\TestAssignment"

REM Display test options
echo ============================================================
echo             Test Execution Options
echo ============================================================
echo.
echo 1. Run all tests (Chrome, Firefox, Edge)
echo 2. Run Chrome tests only
echo 3. Run Firefox tests only
echo 4. Run Edge tests only
echo 5. View test reports
echo 6. Clean build
echo 7. Exit
echo.

set /p choice="Select an option (1-7): "

if "%choice%"=="1" (
    echo.
    echo Running ALL browser tests in headless mode...
    echo.
    call mvn verify
) else if "%choice%"=="2" (
    echo.
    echo Running CHROME tests in headless mode...
    echo.
    call mvn verify -Dbrowser=chrome
) else if "%choice%"=="3" (
    echo.
    echo Running FIREFOX tests in headless mode...
    echo.
    if !FIREFOX_INSTALLED! equ 0 (
        echo [ERROR] Firefox is not installed. Please install Firefox first.
        echo Download from: https://www.mozilla.org/en-US/firefox/new/
        pause
        exit /b 1
    )
    call mvn verify -Dbrowser=firefox
) else if "%choice%"=="4" (
    echo.
    echo Running EDGE tests in headless mode...
    echo.
    if !EDGE_INSTALLED! equ 0 (
        echo [ERROR] Microsoft Edge is not installed. Please install Edge first.
        echo Download from: https://www.microsoft.com/en-us/edge
        pause
        exit /b 1
    )
    call mvn verify -Dbrowser=edge
) else if "%choice%"=="5" (
    echo.
    echo Opening test reports...
    echo.
    if exist "target\cucumber-reports\ui-report.html" (
        start target\cucumber-reports\ui-report.html
        echo Report opened in browser
    ) else (
        echo [WARNING] Test reports not found. Run tests first.
    )
) else if "%choice%"=="6" (
    echo.
    echo Cleaning build directory...
    echo.
    call mvn clean
    echo Build cleaned successfully
) else if "%choice%"=="7" (
    echo.
    echo Exiting...
    exit /b 0
) else (
    echo [ERROR] Invalid choice. Please select 1-7.
)

echo.
echo ============================================================
pause
endlocal

