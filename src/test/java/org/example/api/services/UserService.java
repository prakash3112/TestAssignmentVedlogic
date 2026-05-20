package org.example.api.services;

import io.restassured.response.Response;
import org.example.api.ApiClient;
import org.example.models.UserDto;

/**
 * Service layer wrapping ApiClient for user-related operations.
 * Keeps business logic separate from step definitions and low-level HTTP operations.
 */
public class UserService {
    public Response listUsers(String endpoint) {
        return ApiClient.get(endpoint);
    }

    public Response createUser(String endpoint, UserDto user) {
        return ApiClient.post(endpoint, user);
    }

    public Response updateUser(String endpoint, UserDto user) {
        return ApiClient.put(endpoint, user);
    }

    public Response deleteUser(String endpoint) {
        return ApiClient.delete(endpoint);
    }
}

