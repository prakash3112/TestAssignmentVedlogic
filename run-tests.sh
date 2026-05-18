#!/bin/bash
# Run Both UI and API Tests Using Maven
# Linux/Mac Version

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

echo ""
echo "========================================================"
echo "   UI & API Test Execution - Maven Command Line"
echo "========================================================"
echo ""

# Display menu
echo "Select test execution option:"
echo "1. Run all UI tests"
echo "2. Run all API tests"
echo "3. Run both UI and API tests"
echo "4. Run Chrome UI tests only (headless)"
echo "5. Run Firefox UI tests only (headless)"
echo "6. Run Edge UI tests only (headless)"
echo "7. Run tests with detailed output"
echo "8. View test reports"
echo "9. Clean build"
echo "10. Exit"
echo ""
read -p "Select option (1-10): " choice

case $choice in
    1)
        echo ""
        echo "Running UI Tests..."
        echo ""
        mvn test -Dtest=UITestRunner -X
        ;;
    2)
        echo ""
        echo "Running API Tests..."
        echo ""
        mvn test -Dtest=TestRunner -X
        ;;
    3)
        echo ""
        echo "Running ALL Tests (UI + API)..."
        echo ""
        mvn verify -X
        ;;
    4)
        echo ""
        echo "Running Chrome UI Tests in Headless Mode..."
        echo ""
        mvn test -Dtest=UITestRunner -Dbrowser=chrome -X
        ;;
    5)
        echo ""
        echo "Running Firefox UI Tests in Headless Mode..."
        echo ""
        mvn test -Dtest=UITestRunner -Dbrowser=firefox -X
        ;;
    6)
        echo ""
        echo "Running Edge UI Tests in Headless Mode..."
        echo ""
        mvn test -Dtest=UITestRunner -Dbrowser=edge -X
        ;;
    7)
        echo ""
        echo "Running All Tests with Verbose Output..."
        echo ""
        mvn verify -X -e
        ;;
    8)
        echo ""
        echo "Opening test reports..."
        echo ""
        if [ -f "target/cucumber-reports/ui-report.html" ]; then
            if command -v xdg-open > /dev/null; then
                xdg-open "target/cucumber-reports/ui-report.html"
            elif command -v open > /dev/null; then
                open "target/cucumber-reports/ui-report.html"
            else
                echo "HTML report location: target/cucumber-reports/ui-report.html"
            fi
        else
            echo "Reports not found. Run tests first."
        fi
        ;;
    9)
        echo ""
        echo "Cleaning build directory..."
        echo ""
        mvn clean
        echo "Build cleaned successfully"
        ;;
    10)
        echo "Exiting..."
        exit 0
        ;;
    *)
        echo "Invalid option. Please select 1-10."
        exit 1
        ;;
esac

echo ""
echo "========================================================"

