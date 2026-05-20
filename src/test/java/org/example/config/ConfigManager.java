package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple configuration manager for tests.
 * Loads properties from classpath `test-config.properties` and allows
 * system property overrides.
 */
public class ConfigManager {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream("test-config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            System.err.println("Unable to load test-config.properties: " + e.getMessage());
        }
    }

    public static String getUiBaseUrl() {
        return System.getProperty("ui.base.url", props.getProperty("ui.base.url", "https://www.saucedemo.com/"));
    }

    public static String getApiBaseUrl() {
        return System.getProperty("api.base.url", props.getProperty("api.base.url", "http://localhost:8080"));
    }

    public static long getExplicitWaitSeconds() {
        String v = System.getProperty("wait.explicit.seconds", props.getProperty("wait.explicit.seconds", "10"));
        try {
            return Long.parseLong(v);
        } catch (NumberFormatException e) {
            return 10L;
        }
    }

    public static boolean isHeadless() {
        String v = System.getProperty("headless", props.getProperty("headless", "true"));
        return Boolean.parseBoolean(v);
    }

    public static String getDefaultBrowser() {
        return System.getProperty("browser", props.getProperty("browser", "chrome")).toLowerCase();
    }
}

