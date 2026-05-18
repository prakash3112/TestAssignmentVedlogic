package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Test Runner for UI Tests
 * Executes tests with @UI tag
 * Configured for parallel execution with cross-browser support
 * Thread-safe WebDriver management using ThreadLocal
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.steps", "org.example.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/ui-report.html",
        "json:target/cucumber-reports/ui-report.json",
        "junit:target/cucumber-reports/ui-report.xml"
    },
    monochrome = true,
    tags = "@UI",
    dryRun = false,
   // strict = false,
    publish = true
)
public class UITestRunner {
}

