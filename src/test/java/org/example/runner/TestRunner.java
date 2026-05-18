package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Test Runner for API Tests
 * Executes tests with @API tag
 * Configured for parallel execution
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.steps", "org.example.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/api-report.html",
        "json:target/cucumber-reports/api-report.json"
    },
    monochrome = true,
    tags = "@API",
    dryRun = false
)
public class TestRunner {
}
