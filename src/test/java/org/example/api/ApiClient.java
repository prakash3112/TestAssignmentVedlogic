package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiClient {
    private static final String BASE_URI = "https://reqres.in";

    static {
        RestAssured.baseURI = BASE_URI;
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
}
