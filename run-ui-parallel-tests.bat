@echo off
REM ===============================================
REM UI Parallel Execution - Cross-Browser Tests
REM ===============================================

echo ===============================================
echo Starting Parallel UI Tests - Cross-Browser
echo ===============================================
echo.

REM Run UI tests with 4 parallel threads
REM Each thread will execute a different scenario concurrently
mvn clean test ^
  -Dparallel=methods ^
  -DthreadCount=4 ^
  -DforkCount=1 ^
  -Dcucumber.filter.tags="@UI" ^
  -Dtest=UITestRunner

echo.
echo ===============================================
echo Parallel UI Tests Completed
echo ===============================================
echo.

REM Optional: Run specific browser tests
REM mvn clean test -Dcucumber.filter.tags="@UI and @Chrome"
REM mvn clean test -Dcucumber.filter.tags="@UI and @Firefox"
REM mvn clean test -Dcucumber.filter.tags="@UI and @Edge"

pause

