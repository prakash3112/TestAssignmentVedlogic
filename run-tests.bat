@echo off
REM ================================================
REM  UI & API Tests - Maven Command Line Execution
REM  Windows Version
REM ================================================

setlocal enabledelayedexpansion
cd /d "%~dp0"

:menu
cls
echo.
echo ================================================
echo   UI ^& API Tests - Maven Execution Menu
echo ================================================
echo.
echo UI TEST OPTIONS:
echo   1. Run all UI tests (parallel)
echo   2. Run Chrome UI tests (headless)
echo   3. Run Firefox UI tests (headless)
echo   4. Run Edge UI tests (headless)
echo.
echo API TEST OPTIONS:
echo   5. Run all API tests
echo.
echo COMBINED OPTIONS:
echo   6. Run both UI and API tests (complete suite)
echo   7. Run UI tests only (all browsers)
echo.
echo BUILD OPTIONS:
echo   8. Clean build
echo   9. Compile only
echo   10. Install dependencies
echo.
echo REPORT OPTIONS:
echo   11. View HTML test report
echo   12. View JSON test data
echo.
echo OTHER:
echo   13. Run with verbose output (all tests)
echo   14. Help
echo   15. Exit
echo.

set /p choice="Select option (1-15): "

if "%choice%"=="1" (
    call :run_ui_all
) else if "%choice%"=="2" (
    call :run_chrome
) else if "%choice%"=="3" (
    call :run_firefox
) else if "%choice%"=="4" (
    call :run_edge
) else if "%choice%"=="5" (
    call :run_api
) else if "%choice%"=="6" (
    call :run_all
) else if "%choice%"=="7" (
    call :run_ui_all
) else if "%choice%"=="8" (
    call :clean_build
) else if "%choice%"=="9" (
    call :compile_only
) else if "%choice%"=="10" (
    call :install_deps
) else if "%choice%"=="11" (
    call :view_html_report
) else if "%choice%"=="12" (
    call :view_json_report
) else if "%choice%"=="13" (
    call :run_verbose
) else if "%choice%"=="14" (
    call :show_help
) else if "%choice%"=="15" (
    echo Exiting...
    exit /b 0
) else (
    echo Invalid option selected.
)

pause
goto menu

:run_ui_all
cls
echo.
echo ================================================
echo   Running ALL UI Tests (Chrome, Firefox, Edge)
echo ================================================
echo.
echo Command: mvn test -Dtest=UITestRunner
echo.
mvn test -Dtest=UITestRunner
goto :eof

:run_chrome
cls
echo.
echo ================================================
echo   Running Chrome UI Tests (Headless Mode)
echo ================================================
echo.
echo Command: mvn test -Dtest=UITestRunner -Dbrowser=chrome
echo.
mvn test -Dtest=UITestRunner -Dbrowser=chrome
goto :eof

:run_firefox
cls
echo.
echo ================================================
echo   Running Firefox UI Tests (Headless Mode)
echo ================================================
echo.
echo NOTE: Firefox must be installed first
echo Install with: choco install firefox -y
echo.
echo Command: mvn test -Dtest=UITestRunner -Dbrowser=firefox
echo.
mvn test -Dtest=UITestRunner -Dbrowser=firefox
goto :eof

:run_edge
cls
echo.
echo ================================================
echo   Running Edge UI Tests (Headless Mode)
echo ================================================
echo.
echo NOTE: Edge must be installed first
echo Install with: choco install microsoft-edge -y
echo.
echo Command: mvn test -Dtest=UITestRunner -Dbrowser=edge
echo.
mvn test -Dtest=UITestRunner -Dbrowser=edge
goto :eof

:run_api
cls
echo.
echo ================================================
echo   Running ALL API Tests
echo ================================================
echo.
echo Command: mvn test -Dtest=TestRunner
echo.
mvn test -Dtest=TestRunner
goto :eof

:run_all
cls
echo.
echo ================================================
echo   Running COMPLETE TEST SUITE
echo   (UI + API Tests in Parallel)
echo ================================================
echo.
echo Command: mvn verify
echo.
mvn verify
goto :eof

:run_ui_all
cls
echo.
echo ================================================
echo   Running UI Tests Only
echo ================================================
echo.
echo Command: mvn test -Dtest=UITestRunner
echo.
mvn test -Dtest=UITestRunner
goto :eof

:clean_build
cls
echo.
echo ================================================
echo   Clean Build
echo ================================================
echo.
echo Command: mvn clean
echo.
mvn clean
echo Build cleaned successfully!
goto :eof

:compile_only
cls
echo.
echo ================================================
echo   Compile Only (No Tests)
echo ================================================
echo.
echo Command: mvn test-compile
echo.
mvn test-compile
goto :eof

:install_deps
cls
echo.
echo ================================================
echo   Install Dependencies
echo ================================================
echo.
echo Command: mvn clean install
echo.
mvn clean install
goto :eof

:view_html_report
cls
echo.
echo ================================================
echo   Opening HTML Test Report
echo ================================================
echo.
if exist "target\cucumber-reports\ui-report.html" (
    start target\cucumber-reports\ui-report.html
    echo Report opened in browser
) else (
    echo HTML report not found at: target\cucumber-reports\ui-report.html
    echo Run tests first to generate reports
)
goto :eof

:view_json_report
cls
echo.
echo ================================================
echo   JSON Test Report
echo ================================================
echo.
if exist "target\cucumber-reports\ui-report.json" (
    type target\cucumber-reports\ui-report.json
) else (
    echo JSON report not found
    echo Run tests first to generate reports
)
goto :eof

:run_verbose
cls
echo.
echo ================================================
echo   Running Tests with Verbose Output
echo ================================================
echo.
echo Command: mvn verify -X -e
echo.
mvn verify -X -e
goto :eof

:show_help
cls
echo.
echo ================================================
echo   MAVEN COMMAND REFERENCE
echo ================================================
echo.
echo UI TESTS:
echo   mvn test -Dtest=UITestRunner
echo   mvn test -Dtest=UITestRunner -Dbrowser=chrome
echo   mvn test -Dtest=UITestRunner -Dbrowser=firefox
echo   mvn test -Dtest=UITestRunner -Dbrowser=edge
echo.
echo API TESTS:
echo   mvn test -Dtest=TestRunner
echo.
echo COMBINED:
echo   mvn verify                    (All tests)
echo   mvn verify -X                 (With debug output)
echo   mvn verify -e                 (Show errors)
echo.
echo BUILD:
echo   mvn clean                     (Clean build)
echo   mvn test-compile              (Compile tests)
echo   mvn clean install             (Full setup)
echo.
echo For more information:
echo   - See MAVEN_COMMAND_REFERENCE.md
echo   - See QUICK_HEADLESS_REFERENCE.md
echo.
goto :eof

endlocal

