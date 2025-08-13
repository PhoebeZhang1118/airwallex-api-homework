package com.airwallex.api;

import com.airwallex.utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthManager {

    public static String getAuthToken() {
        Response response = RestAssured.given()
                .baseUri(Config.getBaseUrl())
                .basePath(Config.getAuthEndpoint())
                .header("Content-Type", "application/json")
                .header("x-client-id", Config.getClientId())
                .header("x-api-key", Config.getApiKey())
                .post();

        if (response.statusCode() != 201) {
            throw new RuntimeException("Failed to get auth token: " + response.statusCode() + " - " + response.body().asString());
        }
        String token = response.jsonPath().getString("token");
        if (token == null || token.isEmpty()){
            throw new RuntimeException("Token not found in response body");
        }
        return token;
    }

}
