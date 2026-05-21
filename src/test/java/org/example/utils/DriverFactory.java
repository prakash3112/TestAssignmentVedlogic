package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.example.config.ConfigManager;


public class DriverFactory {


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> currentBrowser = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call getDriver(browser) from Hooks before using pages.");
        }
        return driver.get();
    }


    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            String effective = browser != null ? browser : ConfigManager.getDefaultBrowser();
            System.out.println("Initializing " + effective + " driver for Thread ID: " +
                             Thread.currentThread().getId());

            switch (effective.toLowerCase().trim()) {
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
            currentBrowser.set(effective.toLowerCase());
        }
        return driver.get();
    }


    private static void initializeChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        // Browser options
        chromeOptions.addArguments(

        );

        if (ConfigManager.isHeadless()) {
            chromeOptions.addArguments("--headless=new");
        }

        // Performance options
        chromeOptions.addArguments("--disable-gpu", "--no-sandbox");

        driver.set(new ChromeDriver(chromeOptions));
        System.out.println("Chrome WebDriver initialized for Thread ID: " +
                         Thread.currentThread().getId());
    }


      private static void initializeFirefox() {
          WebDriverManager.firefoxdriver().setup();
          FirefoxOptions firefoxOptions = new FirefoxOptions();

           // Browser options
           firefoxOptions.addArguments(

           );

           if (ConfigManager.isHeadless()) {
               firefoxOptions.addArguments("--headless");
           }

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

      private static String findFirefoxBinary() {
          String osName = System.getProperty("os.name").toLowerCase();

          if (osName.contains("win")) {

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

              String macPath = "/Applications/Firefox.app/Contents/MacOS/firefox";
              if (new java.io.File(macPath).exists()) {
                  return macPath;
              }
          } else if (osName.contains("linux")) {

              String linuxPath = "/usr/bin/firefox";
              if (new java.io.File(linuxPath).exists()) {
                  return linuxPath;
              }
          }

          return null;
      }

    private static void initializeEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();

        // Browser options
        edgeOptions.addArguments(

        );

        if (ConfigManager.isHeadless()) {
            edgeOptions.addArguments("--headless=new");
        }

        edgeOptions.addArguments("--disable-gpu", "--no-sandbox");

        driver.set(new EdgeDriver(edgeOptions));
        System.out.println("Edge WebDriver initialized for Thread ID: " +
                         Thread.currentThread().getId());
    }


    public static WebDriver getCurrentDriver() {
        return driver.get();
    }

    public static String getCurrentBrowser() {
        String browser = currentBrowser.get();
        return browser != null ? browser : "chrome";
    }

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


    public static void resetThreadLocal() {
        driver.remove();
        currentBrowser.remove();
    }


    public static boolean isDriverInitialized() {
        return driver.get() != null;
    }
}
