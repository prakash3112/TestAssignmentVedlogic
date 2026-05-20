Maven Run Commands - UI and API Tests

This document shows how to run UI and API tests from the command line using Maven and runtime configuration.

General notes
- Configuration is read from `src/test/resources/test-config.properties` and can be overridden via system properties on the command line.
- Relevant properties: `browser` (chrome|firefox|edge), `headless` (true|false), `ui.base.url`, `api.base.url`, `wait.explicit.seconds`.

Run UI tests (Cucumber @UI) using the JUnit runner
- Run only the UI runner class (UITestRunner):

```powershell
cd C:\Users\hp\IdeaProjects\TestAssignment
mvn test -Dtest=UITestRunner -Dbrowser=chrome -Dheadless=true
```

- Example: Run UI tests in Firefox headed mode

```powershell
mvn test -Dtest=UITestRunner -Dbrowser=firefox -Dheadless=false
```

Run API tests (Cucumber @API) using the JUnit runner

```powershell
mvn test -Dtest=TestRunner
```

Run both UI and API (all runners included by default)

```powershell
mvn test
```

Using Maven Failsafe (integration-test / verify)
- The project is configured to include runner classes under failsafe as well. To run integration flow via failsafe:

```powershell
mvn verify -Dtest=UITestRunner -Dbrowser=edge -Dheadless=true
```

Configuration overrides
- Provide any config property as a system property, e.g.:
  - `-Dui.base.url=https://staging.example.com`
  - `-Dapi.base.url=http://localhost:8081`
  - `-Dwait.explicit.seconds=20`

Examples
- Quick Chrome headless UI run:

```powershell
mvn test -Dtest=UITestRunner -Dbrowser=chrome -Dheadless=true
```

- Run API tests only:

```powershell
mvn test -Dtest=TestRunner
```

Notes and troubleshooting
- Drivers are managed automatically by WebDriverManager. The first run will download the appropriate browser drivers (requires internet access).
- If browser binary (Firefox) is installed in a non-standard location, set the path via system property or ensure the binary is in the default install location.
- To debug failing driver downloads due to network, download driver executables manually and add them to PATH or set system properties (e.g., `webdriver.gecko.driver`).

If you want, I can add convenience scripts (PowerShell / batch) that wrap the most common commands.

