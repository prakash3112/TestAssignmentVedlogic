package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.config.ConfigManager;

import java.util.Map;

/**
 * Low-level API client. Keeps RestAssured configuration centralized and
 * exposes helper methods for simple HTTP verbs. Base URI is read from
 * `ConfigManager` and can be overridden via system properties.
 */
public class ApiClient {
    static {
        RestAssured.baseURI = ConfigManager.getApiBaseUrl();
    }

    public static Response get(String endpoint) {
        return RestAssured.given()
                .get(endpoint);
    }

    public static Response post(String endpoint, Map<String, String> body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(endpoint);
    }

    public static Response put(String endpoint, Map<String, String> body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .put(endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return RestAssured.given()
                .delete(endpoint);
    }
}
