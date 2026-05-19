# Test Automation Framework (UI + API)

This repository contains a working Cucumber-based test automation framework for UI and API tests. It uses:

- Selenium 4 with Page Object Model for UI flows
- Cucumber (JUnit) for BDD-style scenarios
- Rest Assured for API requests
- WebDriverManager to manage browser drivers at runtime

This README reflects the current implemented state and how to run and configure the tests.

## Project layout (relevant folders)

- `src/test/java/org/example/pages` — Page Object Model classes (constructor injection of WebDriver)
- `src/test/java/org/example/steps` — Cucumber step definitions (lazy page initialization)
- `src/test/java/org/example/hooks` — Cucumber hooks (single place to initialize/quit WebDriver)
- `src/test/java/org/example/runner` — JUnit/Cucumber runners (`UITestRunner`, `TestRunner`)
- `src/test/java/org/example/utils` — Utilities (e.g., `DriverFactory`)
- `src/test/java/org/example/config` — Test configuration helper (`ConfigManager`)
- `src/test/resources` — Test resources and `test-config.properties`
- `src/test/resources/features` — Cucumber feature files

## What is implemented (current capabilities)

- UI: SauceDemo login + add-to-cart + checkout flows (page objects under `pages`)
- API: Basic API feature scaffolding (feature files under `features`)
- Cross-browser support: Chrome, Firefox, Edge via `DriverFactory` and WebDriverManager
- Parallel execution support using ThreadLocal WebDriver instances (configured in the runners and Maven)
- Configuration externalized in `src/test/resources/test-config.properties` and overridable via `-D` system properties
- Headless mode configurable via `-Dheadless=true|false`

## Key design decisions

- Page objects receive a `WebDriver` instance via constructor (no hidden driver creation)
- `Hooks` is the single authorized place to create/quit WebDriver instances
- `DriverFactory` manages ThreadLocal drivers and respects config for headless/browser selection
- `ConfigManager` reads defaults from `test-config.properties` and allows system overrides

## Prerequisites

- Java 8 (project `pom.xml` is set to compile for Java 8)
- Maven 3.6+
- Browsers: Chrome, Firefox, Edge (install at system level). WebDriver binaries are handled by WebDriverManager but browser binary must be present.

Note: If Firefox is installed in a non-standard location on Windows, `DriverFactory` attempts to auto-detect the Firefox binary. You may also install Firefox or set the binary path via system properties.

## Configuration

Default configuration file: `src/test/resources/test-config.properties`.

Important properties (can be overridden with `-D`):

- `ui.base.url` — base URL for UI tests (default: `https://www.saucedemo.com/`)
- `api.base.url` — base URL for API tests
- `wait.explicit.seconds` — explicit wait timeout used by `BasePage`
- `headless` — `true` or `false` (controls whether browsers run headless)
- `browser` — default browser (`chrome` | `firefox` | `edge`)

You can override any of these values via Maven system properties, for example:

```powershell
mvn test -Dtest=UITestRunner -Dbrowser=firefox -Dheadless=false
```

## How to run

Run UI tests only (UITestRunner):

```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome -Dheadless=true
```

Run API tests only (TestRunner):

```powershell
mvn test -Dtest=TestRunner
```

Run all tests (default):

```powershell
mvn test
```

See `MAVEN_RUN_COMMANDS.md` for additional examples and recommended command-line combinations.

## Notes, limitations and troubleshooting

- WebDriverManager downloads drivers at runtime and requires internet access on the first run. If your environment blocks downloads, manually place driver executables in PATH or configure system properties (`webdriver.gecko.driver`, etc.).
- Firefox may require the browser binary to be installed. If you see "Firefox binary not found" errors, install Firefox or adjust the `DriverFactory` to point at the binary.
- Cucumber constructs step definition instances before `@Before` hooks run. To avoid early driver creation, pages use constructor injection and steps use lazy getters — ensure hooks are initializing the driver before the step methods access pages.

## Next improvement suggestions (planned)

- Refactor API layer to introduce `ApiClient` + service layer + DTOs for scalability
- Add utility methods to `InventoryPage` to select products by name (instead of `:first-child` selectors)
- Add CI pipeline example and Docker-based browser alternatives (selenium/standalone)
- Clean up repository by removing unused files and updating documentation further

If you want me to apply any of these improvements, pick one (API refactor, page flexibility, CI integration, or repo cleanup) and I'll implement it next.
