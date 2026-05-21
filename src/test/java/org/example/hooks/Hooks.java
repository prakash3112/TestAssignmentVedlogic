package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.utils.DriverFactory;
import org.example.config.ConfigManager;

public class Hooks {

    private String browser;


    @Before("@UI")
    public void setUp(Scenario scenario) {
        // Get browser from system property or feature tag or default from config
        browser = System.getProperty("browser", ConfigManager.getDefaultBrowser());

        // Extract browser from scenario tags if present
        if (scenario.getSourceTagNames().contains("@Chrome")) {
            browser = "chrome";
        } else if (scenario.getSourceTagNames().contains("@Firefox")) {
            browser = "firefox";
        } else if (scenario.getSourceTagNames().contains("@Edge")) {
            browser = "edge";
        }

        System.out.println("Thread ID: " + Thread.currentThread().getId() +
                         " | Scenario: " + scenario.getName() +
                         " | Browser: " + browser);

        DriverFactory.getDriver(browser);
    }

    @After("@UI")
    public void tearDown(Scenario scenario) {
        System.out.println("Closing WebDriver for Thread ID: " +
                         Thread.currentThread().getId() +
                         " | Scenario: " + scenario.getName());
        DriverFactory.quitDriver();
    }


    @Before("@API")
    public void setUpApi(Scenario scenario) {
        System.out.println("Starting API Test: " + scenario.getName());
    }


    @After("@API")
    public void tearDownApi(Scenario scenario) {
        System.out.println("Completed API Test: " + scenario.getName());
    }
}

