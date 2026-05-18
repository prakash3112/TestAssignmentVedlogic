# Headless Browser UI Testing - Setup & Execution Script
# Compatible with Windows 10+
# Run with: powershell -ExecutionPolicy Bypass -File run-headless-tests.ps1

Set-StrictMode -Version Latest
$ErrorActionPreference = "Continue"

# Color functions
function Write-Success {
    param([string]$Message)
    Write-Host $Message -ForegroundColor Green
}

function Write-Warning {
    param([string]$Message)
    Write-Host $Message -ForegroundColor Yellow
}

function Write-Error-Custom {
    param([string]$Message)
    Write-Host $Message -ForegroundColor Red
}

# Header
Write-Host ""
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host "  Headless Browser UI Testing - Setup & Execution" -ForegroundColor Cyan
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host ""

# Browser detection function
function Test-BrowserInstalled {
    param(
        [string]$Name,
        [string[]]$Paths
    )

    Write-Host "Checking for $Name..." -ForegroundColor Cyan

    foreach ($path in $Paths) {
        if (Test-Path $path) {
            Write-Success "[OK] $Name found at: $path"
            return $true
        }
    }

    Write-Warning "[WARNING] $Name not found"
    return $false
}

# Check browsers
$chromePaths = @(
    "C:\Program Files\Google\Chrome\Application\chrome.exe",
    "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
)
$chromeInstalled = Test-BrowserInstalled "Google Chrome" $chromePaths

Write-Host ""

$firefoxPaths = @(
    "C:\Program Files\Mozilla Firefox\firefox.exe",
    "C:\Program Files (x86)\Mozilla Firefox\firefox.exe"
)
$firefoxInstalled = Test-BrowserInstalled "Mozilla Firefox" $firefoxPaths

Write-Host ""

$edgePaths = @(
    "C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe",
    "C:\Program Files\Microsoft\Edge\Application\msedge.exe"
)
$edgeInstalled = Test-BrowserInstalled "Microsoft Edge" $edgePaths

# Summary
Write-Host ""
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host "              Browser Installation Summary" -ForegroundColor Cyan
Write-Host "============================================================" -ForegroundColor Cyan

if ($chromeInstalled) {
    Write-Success "[✓] Chrome: INSTALLED"
} else {
    Write-Error-Custom "[✗] Chrome: NOT INSTALLED"
}

if ($firefoxInstalled) {
    Write-Success "[✓] Firefox: INSTALLED"
} else {
    Write-Error-Custom "[✗] Firefox: NOT INSTALLED"
}

if ($edgeInstalled) {
    Write-Success "[✓] Edge: INSTALLED"
} else {
    Write-Error-Custom "[✗] Edge: NOT INSTALLED"
}

Write-Host ""

# Change to project directory
$projectDir = "C:\Users\hp\IdeaProjects\TestAssignment"
Set-Location $projectDir

# Menu
$menuOptions = @(
    "Run ALL tests (Chrome, Firefox, Edge)",
    "Run Chrome tests only",
    "Run Firefox tests only",
    "Run Edge tests only",
    "View test reports",
    "Clean build",
    "Exit"
)

Write-Host "============================================================" -ForegroundColor Cyan
Write-Host "              Test Execution Options" -ForegroundColor Cyan
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host ""

for ($i = 0; $i -lt $menuOptions.Count; $i++) {
    Write-Host "$($i+1). $($menuOptions[$i])"
}

Write-Host ""
$choice = Read-Host "Select an option (1-7)"

Write-Host ""

switch ($choice) {
    "1" {
        Write-Host "Running ALL browser tests in headless mode..." -ForegroundColor Cyan
        Write-Host ""
        mvn verify
    }
    "2" {
        Write-Host "Running CHROME tests in headless mode..." -ForegroundColor Cyan
        Write-Host ""
        mvn verify -Dbrowser=chrome
    }
    "3" {
        Write-Host "Running FIREFOX tests in headless mode..." -ForegroundColor Cyan
        Write-Host ""
        if (-not $firefoxInstalled) {
            Write-Error-Custom "[ERROR] Firefox is not installed."
            Write-Host "Please install Firefox from: https://www.mozilla.org/en-US/firefox/new/"
            Read-Host "Press Enter to exit"
            exit 1
        }
        mvn verify -Dbrowser=firefox
    }
    "4" {
        Write-Host "Running EDGE tests in headless mode..." -ForegroundColor Cyan
        Write-Host ""
        if (-not $edgeInstalled) {
            Write-Error-Custom "[ERROR] Microsoft Edge is not installed."
            Write-Host "Please install Edge from: https://www.microsoft.com/en-us/edge"
            Read-Host "Press Enter to exit"
            exit 1
        }
        mvn verify -Dbrowser=edge
    }
    "5" {
        Write-Host "Opening test reports..." -ForegroundColor Cyan
        Write-Host ""
        $reportPath = Join-Path $projectDir "target\cucumber-reports\ui-report.html"
        if (Test-Path $reportPath) {
            Invoke-Item $reportPath
            Write-Success "Report opened in browser"
        } else {
            Write-Warning "[WARNING] Test reports not found. Run tests first."
        }
    }
    "6" {
        Write-Host "Cleaning build directory..." -ForegroundColor Cyan
        Write-Host ""
        mvn clean
        Write-Success "Build cleaned successfully"
    }
    "7" {
        Write-Host "Exiting..." -ForegroundColor Yellow
        exit 0
    }
    default {
        Write-Error-Custom "[ERROR] Invalid choice. Please select 1-7."
    }
}

Write-Host ""
Write-Host "============================================================" -ForegroundColor Cyan
Read-Host "Press Enter to exit"

