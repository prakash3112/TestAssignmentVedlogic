package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

/**
 * DriverFactory for Cross-Browser Execution
 * Thread-safe WebDriver management using ThreadLocal
 * Supports Chrome, Firefox, and Edge browsers
 * Optimized for parallel test execution
 */
public class DriverFactory {

    /**
     * ThreadLocal variable to maintain separate WebDriver instance per thread
     * Ensures thread safety in parallel execution
     */
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> currentBrowser = new ThreadLocal<>();

    /**
     * Get WebDriver with default Chrome browser
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return getDriver("chrome");
    }

    /**
     * Get WebDriver for specified browser
     * Thread-safe implementation for parallel execution
     *
     * @param browser Browser type (chrome, firefox, edge)
     * @return WebDriver instance
     */
    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            System.out.println("Initializing " + browser + " driver for Thread ID: " +
                             Thread.currentThread().getId());

            switch (browser.toLowerCase().trim()) {
                case "firefox":
                    initializeFirefox();
                    break;
                case "edge":
                    initializeEdge();
                    break;
                case "chrome":
                default:
                    initializeChrome();
                    break;
            }
            currentBrowser.set(browser.toLowerCase());
        }
        return driver.get();
    }

    /**
     * Initialize Chrome WebDriver with optimized options
     */
    private static void initializeChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        // Browser options
        chromeOptions.addArguments(
            "--start-maximized",
            "--disable-notifications",
            "--incognito",
            "--disable-popup-blocking",
            "--disable-extensions",
            "--disable-blink-features=AutomationControlled",
            "--headless=new"
        );

        // Performance options
        chromeOptions.addArguments("--disable-gpu", "--no-sandbox");

        driver.set(new ChromeDriver(chromeOptions));
        System.out.println("Chrome WebDriver initialized for Thread ID: " +
                         Thread.currentThread().getId());
    }

     /**
      * Initialize Firefox WebDriver with optimized options
      */
      private static void initializeFirefox() {
          WebDriverManager.firefoxdriver().setup();
          FirefoxOptions firefoxOptions = new FirefoxOptions();

          // Browser options
          firefoxOptions.addArguments(
              "--width=1920",
              "--height=1080",
              "-private",
              "--headless"
          );

          // Set Firefox binary path for Windows
          String firefoxPath = findFirefoxBinary();
          if (firefoxPath != null && !firefoxPath.isEmpty()) {
              firefoxOptions.setBinary(firefoxPath);
              System.out.println("Firefox binary path set to: " + firefoxPath);
          }

          driver.set(new FirefoxDriver(firefoxOptions));
          System.out.println("Firefox WebDriver initialized for Thread ID: " +
                           Thread.currentThread().getId());
      }

      /**
       * Find Firefox binary path on the system
       */
      private static String findFirefoxBinary() {
          String osName = System.getProperty("os.name").toLowerCase();

          if (osName.contains("win")) {
              // Windows paths for Firefox
              String[] firefoxPaths = {
                  "C:\\Program Files\\Mozilla Firefox\\firefox.exe",
                  "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe",
                  "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Mozilla Firefox\\firefox.exe"
              };

              for (String path : firefoxPaths) {
                  java.io.File file = new java.io.File(path);
                  if (file.exists()) {
                      return path;
                  }
              }
          } else if (osName.contains("mac")) {
              // macOS path
              String macPath = "/Applications/Firefox.app/Contents/MacOS/firefox";
              if (new java.io.File(macPath).exists()) {
                  return macPath;
              }
          } else if (osName.contains("linux")) {
              // Linux path
              String linuxPath = "/usr/bin/firefox";
              if (new java.io.File(linuxPath).exists()) {
                  return linuxPath;
              }
          }

          return null;
      }

    /**
     * Initialize Edge WebDriver with optimized options
     */
    private static void initializeEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();

        // Browser options
        edgeOptions.addArguments(
            "--start-maximized",
            "--disable-notifications",
            "--incognito",
            "--disable-popup-blocking",
            "--disable-extensions",
            "--headless=new"
        );

        // Performance options
        edgeOptions.addArguments("--disable-gpu", "--no-sandbox");

        driver.set(new EdgeDriver(edgeOptions));
        System.out.println("Edge WebDriver initialized for Thread ID: " +
                         Thread.currentThread().getId());
    }

    /**
     * Get current WebDriver instance
     * @return WebDriver instance or null if not initialized
     */
    public static WebDriver getCurrentDriver() {
        return driver.get();
    }

    /**
     * Get current browser name
     * @return Browser name (chrome, firefox, edge)
     */
    public static String getCurrentBrowser() {
        String browser = currentBrowser.get();
        return browser != null ? browser : "chrome";
    }

    /**
     * Quit WebDriver and clean up resources
     * Thread-safe implementation
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            try {
                String browser = currentBrowser.get();
                System.out.println("Quitting " + browser + " driver for Thread ID: " +
                                 Thread.currentThread().getId());
                webDriver.quit();
                System.out.println(browser + " driver quit successfully");
            } catch (Exception e) {
                System.err.println("Error quitting WebDriver: " + e.getMessage());
                e.printStackTrace();
            } finally {
                driver.remove();
                currentBrowser.remove();
            }
        }
    }

    /**
     * Reset ThreadLocal variables
     * Useful for test cleanup
     */
    public static void resetThreadLocal() {
        driver.remove();
        currentBrowser.remove();
    }

    /**
     * Check if WebDriver is initialized
     * @return true if WebDriver is initialized, false otherwise
     */
    public static boolean isDriverInitialized() {
        return driver.get() != null;
    }
}
