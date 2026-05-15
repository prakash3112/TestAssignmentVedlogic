package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.utils.DriverFactory;

public class Hooks {

    @Before("@UI")
    public void setUp() {
        DriverFactory.getDriver();
    }

    @After("@UI")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
